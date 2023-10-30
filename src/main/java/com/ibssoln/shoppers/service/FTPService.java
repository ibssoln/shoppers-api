package com.ibssoln.shoppers.service;

import com.google.common.base.Stopwatch;
import com.ibssoln.shoppers.domain.exception.ShoppersException;
import com.ibssoln.shoppers.entity.Inventory;
import com.ibssoln.shoppers.dto.FileInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.io.FileUtils;
import com.google.common.collect.Lists;
import static org.apache.commons.lang3.StringUtils.join;

@Service
public class FTPService {
    private static final Logger log = LoggerFactory.getLogger(FTPService.class);
    private static final List<String> COLUMNS = List.of("record_id", "item_id", "item_name", "store_id", "store_name", "store_order_code");

    private String host;
    private String port;
    private String user;
    private String key;
    private String targetFilePath;

    public FileInfo sendInventoryOrderFTP(String itemId, List<Inventory> lowInventories, Map<String, String> shopOrderCodes) throws ShoppersException {
        File dataFile = null;
        String fileName = null;
        try{
            dataFile = File.createTempFile("request-", ".dat", new File("C:/1-test"));
            Long numStores = populateDataFile(dataFile, lowInventories, shopOrderCodes);
            fileName = "request-item-"+itemId+"-numStores-"+numStores+"-"+LocalDateTime.now()+".dat";
            scpFile(dataFile, fileName);
        } catch (IOException e) {
            throw new ShoppersException("FTP failed with IO error.", e);
        } catch (Exception e) {
            log.error("BatchSend - ftp a flat file failed. Error: {}", e.getMessage());
            throw new ShoppersException("FTP failed with error.", e);
        } finally {
            if(Objects.nonNull(dataFile)){
                FileUtils.deleteQuietly(dataFile);
            }
            log.info("Submission of batch file completed for id {}", itemId);
        }
        return FileInfo.builder().fileName(fileName).build();
    }

    private Long populateDataFile(File dataFile, List<Inventory> lowInventories, Map<String, String> shopOrderCodes) throws IOException {
        long index = 0;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))){
            String columnHeader = join(COLUMNS.toArray(), "|");
            writer.write(columnHeader);
            for(Inventory inventory : lowInventories){
                index++;
                writer.newLine();
                List<String> data = Lists.newArrayList(Long.toString(index), inventory.getItem().getId(), inventory.getItem().getName(),
                        inventory.getStore().getId(), inventory.getStore().getName(), shopOrderCodes.get(inventory.getStore().getId()));
                String row = join(data, "|");
                writer.write(row);
            }
        }
        return index;
    }

    private void scpFile(File dataFile, String fileName) throws IOException{  //SshException
        log.info("Start to ftp a flat file {}.", fileName);
        Stopwatch sw = Stopwatch.createStarted();
//        SftpSession session = null;
        try{
//            session = createSftpSession();
//            byte[] sshKey = StringUtils.isBlank(key) ? ScpUtils.getRsaPrivateKey() : key.getBytes();
//            session.connect(host, Integer.valueOf(port), user, sshKey);
            if(Objects.nonNull(dataFile) && StringUtils.isNotBlank(fileName)){
//                session.putFile(file, targetFilePath, fileName);
                log.info("Finished ftping a file named {} of size [{} bytes] in {}", dataFile.getName(), dataFile.length(), sw.stop());
            }else{
                log.error("File has not been sent (ftp skipped).");
            }
        } finally {
//            if(Objects.nonNull(session)){
//                session.disconnect();
//            }
            if(sw.isRunning()){
                sw.stop();
            }
        }
    }

}
















