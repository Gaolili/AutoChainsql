package TestCases;

import Base.BaseSetting;
import CommonFunction.ExcelData;
import com.peersafe.chainsql.core.Submit;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

/**
 * @Author:gaolili
 * @Date:Created in 下午5:10 ${Date}
 * @Description:
 */
public class testUpdateTable extends BaseSetting{

    @BeforeClass
    public void setUP(){
        initChainsql(false);
    }

    @Test(dataProvider = "provideNumbers" )
    public void updateTable(HashMap<String, String> data){

        String caseName= data.get("Casename");
        String tablename = data.get("Tablename");
        String exceptResult= data.get("Result");
        String condition = data.get("Condition");
        String raws = data.get("Raw");

        Reporter.log("【用例名称】："+caseName);
        Reporter.log("【参数】: Tablename"+tablename+"Condition:"+condition+"  Raw:"+raws);

        JSONObject obj;
        obj = c.table(tablename).get(condition).update(raws).submit(Submit.SyncCond.db_success);

        Reporter.log("【返回结果】"+ obj);
        String status = obj.getString("status");

        Reporter.log("【用例结果】: expected:"+exceptResult+"actual:"+status);
        Assert.assertEquals(status,exceptResult);

    }

    @DataProvider(name = "provideNumbers")
    public Object[][]  provieData() throws IOException
    {
        ExcelData data = new ExcelData("testData","renameTable");
        return data.getExcelData();
    }


}
