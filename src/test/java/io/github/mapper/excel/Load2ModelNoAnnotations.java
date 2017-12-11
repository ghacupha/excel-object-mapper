package io.github.mapper.excel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Load2ModelNoAnnotations {

    private String fileName;

    @Before
    public void setUp() throws Exception {

        fileName = getClass().getResource("/excel2.xlsx").getPath();
    }

    @Test
    public void loadingTest() throws Exception {

        File excelFile2 = new File(getClass().getResource("/excel2.xlsx").getPath());

        Map<String, Integer> fieldToColumnIndex = new HashMap<>();
        fieldToColumnIndex.put("fistName", 0);
        fieldToColumnIndex.put("lastName", 1);
        fieldToColumnIndex.put("age", 2);
        fieldToColumnIndex.put("birthdate", 5);
        fieldToColumnIndex.put("fatherName", 8);
        fieldToColumnIndex.put("iq", 11);

        List<UnAnnotizedModel> items2 = null;

        try {
            items2 = ExcelMapper.mapFromExcel(new File("D:\\workspaces\\poijiWorkspace\\excel-object-mapper\\src\\main\\resources\\excel2.xlsx"))
                    .toObjectOf(UnAnnotizedModel.class)
                    .fromSheet(0)
                    .mapFieldFrom(fieldToColumnIndex)
                    .map();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date testDate = formatter.parse("21/07/1989");

        // using string date. java.util.Date not suported
        Assert.assertEquals("jittagorn",items2.get(0).getFistName());
        Assert.assertEquals("pitakmetagoon",items2.get(0).getLastName());
        Assert.assertEquals(new Integer(25),items2.get(0).getAge());
        Assert.assertEquals("21/07/1989",items2.get(0).getBirthdate());
        Assert.assertEquals("abcd1",items2.get(0).getFatherName());
        Assert.assertEquals(false,items2.get(0).getIq());
    }
}
