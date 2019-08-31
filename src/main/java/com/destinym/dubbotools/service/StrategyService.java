package com.destinym.dubbotools.service;

import com.destinym.dubbotools.dal.dao.ProjectDao;
import com.destinym.dubbotools.dal.dao.StrategyDao;
import com.destinym.dubbotools.dal.dao.StrategyProjectAppDao;
import com.destinym.dubbotools.dal.dao.StrategyProjectDao;
import com.destinym.dubbotools.dal.model.Strategy;
import com.destinym.dubbotools.vo.StrategyProjectAppVo;
import com.destinym.dubbotools.vo.StrategyProjectVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author mengliang
 * @date 2019/8/13
 */
@Slf4j
@Service
public class StrategyService {
    @Autowired
    private StrategyDao strategyDao;
    @Autowired
    private StrategyProjectDao strategyProjectDao;
    @Autowired
    private StrategyProjectAppDao strategyProjectAppDao;
    @Autowired
    private ProjectDao projectDao;
    private static String DUBBO_TOOLS_GIT_ROOT_PATH = "/data/dubbo_resolve/git";
    private static String DUBBO_TOOLS_FILE_PATH = "/data/dubbo_resolve/";
    private static String FIX = "/src/main/java/";
    private static String DUBBO_FILE = "dubbo-resolve.properties";
    private static String SERVER_TYPE = "server";


    public void sync(Long id) {
        Strategy strategy = strategyDao.get(id);
        try {
            createDirectory(DUBBO_TOOLS_GIT_ROOT_PATH);

            List<String> rules = new ArrayList<>();
            List<StrategyProjectVo> strategyProjectList = strategyProjectDao.listByStrategyId(id);
            for (int i = 0; i < strategyProjectList.size(); i++) {
                StrategyProjectVo strategyProjectVo = strategyProjectList.get(i);
                cloneOrUpdate(strategyProjectVo);
                List<StrategyProjectAppVo> strategyDetailServiceDtos = strategyProjectAppDao.listByStrategyProjectId(strategyProjectVo.getId());
                rules.addAll(generateDubboResovler(strategyDetailServiceDtos, strategyProjectVo.getProjectName()));
            }

            try {
                writeRulesToFile(rules, strategy.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (SERVER_TYPE.equals(strategy.getType())) {
                for (int i = 0; i < strategyProjectList.size(); i++) {
                    StrategyProjectVo strategyProjectVo = strategyProjectList.get(i);
                    List<StrategyProjectAppVo> strategyDetailServiceDtos = strategyProjectAppDao.listByStrategyProjectId(strategyProjectVo.getId());
                    scpToServer(strategy.getName(), strategyDetailServiceDtos);
                }
            }

            strategy.setSyncStatus("success");
            strategyDao.modify(strategy);
        } catch (Exception e) {
            strategy.setSyncStatus("failed");
            strategyDao.modify(strategy);
        }

    }

    private void scpToServer(String path, List<StrategyProjectAppVo> strategyProjectAppVos) {
        Set<String> ips = new HashSet<>();
        for (StrategyProjectAppVo strategyProjectAppVo : strategyProjectAppVos) {
            ips.add(strategyProjectAppVo.getIp());
        }

        String pathname = DUBBO_TOOLS_FILE_PATH + "/" + path;
        for (String ip : ips) {
            try {
                runCommand(Paths.get(pathname), "scp", DUBBO_FILE, "baseuser@" + ip + ":~/");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private List<String> generateDubboResovler(List<StrategyProjectAppVo> strategyDetailList, String projectName) {
        String parent = DUBBO_TOOLS_GIT_ROOT_PATH + "/" + projectName + "/";
        return strategyDetailList.stream().map(item ->
                generateSingleApp(parent + item.getPath() + FIX + item.getPackageName().replace(".", "/"),
                        item.getPackageName(),
                        item.getIp(),
                        item.getPort()))
                .flatMap(List::stream).collect(Collectors.toList());

    }

    private void writeRulesToFile(List<String> rules, String path) throws IOException {
        String pathname = DUBBO_TOOLS_FILE_PATH + "/" + path;
        createDirectory(pathname);
        String filePath = pathname + "/" + DUBBO_FILE;

        /* 写入Txt文件 */
        File writename = new File(filePath);
        writename.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));
        for (String rule : rules) {
            out.write(rule);
            out.newLine();
        }
        out.flush();
        out.close();
    }

    private List<String> generateSingleApp(String path, String packageName, String ip, Integer port) {
        if (StringUtils.isBlank(ip) || port == null) {
            return Collections.EMPTY_LIST;
        }
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files == null) {
            return Collections.EMPTY_LIST;
        }
        List<String> dubboLine = new ArrayList<>();
        for (File file : files) {
            if (file.isFile()) {
                String line = packageName + "." + file.getName().replace(".java", "");
                line += "=dubbo://" + ip + ":" + port;
                dubboLine.add(line);
            }
        }

        return dubboLine;
    }

    private void createDirectory(String dir) {
        File directory = new File(dir);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    private void cloneOrUpdate(StrategyProjectVo strategyProjectVo) {
        String projectName = strategyProjectVo.getProjectName();
        File projectDirectory = new File(DUBBO_TOOLS_GIT_ROOT_PATH + "/" + projectName);
        if (!projectDirectory.exists()) {
            try {
                runCommand(Paths.get(DUBBO_TOOLS_GIT_ROOT_PATH), "git", "clone", strategyProjectVo.getGitUrl(), projectName);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        try {
            String path = DUBBO_TOOLS_GIT_ROOT_PATH + "/" + projectName;
            runCommand(Paths.get(path), "git", "checkout", strategyProjectVo.getGitBranch());
            runCommand(Paths.get(path), "git", "pull");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void runCommand(Path directory, String... command) throws IOException, InterruptedException {
        Objects.requireNonNull(directory, "directory");
        if (!Files.exists(directory)) {
            throw new RuntimeException("can't run command in non-existing directory '" + directory + "'");
        }
        log.info("[run cmd: directory {},  cmd {}]", directory.toFile().getAbsolutePath(), command);
        ProcessBuilder pb = new ProcessBuilder()
                .command(command)
                .directory(directory.toFile());
        Process p = pb.start();
        StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), "ERROR");
        StreamGobbler outputGobbler = new StreamGobbler(p.getInputStream(), "OUTPUT");
        outputGobbler.start();
        errorGobbler.start();
        int exit = p.waitFor();
        errorGobbler.join();
        outputGobbler.join();
        if (exit != 0) {
            throw new AssertionError(String.format("runCommand returned %d", exit));
        }
    }

    private static class StreamGobbler extends Thread {

        private final InputStream is;
        private final String type;

        private StreamGobbler(InputStream is, String type) {
            this.is = is;
            this.type = type;
        }

        @Override
        public void run() {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is));) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(type + "> " + line);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

}
