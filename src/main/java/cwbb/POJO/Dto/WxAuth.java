package cwbb.POJO.Dto;


import lombok.Data;

@Data
public class WxAuth {

    private String encryptedData;
    private String vi;
    private String sessionId;
}
