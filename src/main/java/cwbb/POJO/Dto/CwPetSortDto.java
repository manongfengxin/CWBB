package cwbb.POJO.Dto;


import cwbb.POJO.doMain.CwPetSort;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CwPetSortDto {

    private int psid;

    private String psname;

    private String msg;
}
