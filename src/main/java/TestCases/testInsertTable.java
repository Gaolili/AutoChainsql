package TestCases;

import Base.BaseSetting;
import CommonFunction.Assertion;
import CommonFunction.ExcelData;
import com.peersafe.chainsql.core.Submit;
import com.peersafe.chainsql.util.Util;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class testInsertTable extends BaseSetting{
    @BeforeClass
    public void  setUP(){
        initChainsql(false);// 建表每次都需要更换账户，这样做将来做持续集成时方便，就不用更换表名了
    }

    @Test(dataProvider = "provideNumbers" )
    public void insertTable(HashMap<String, String> data){
        String caseName= data.get("Casename");
        String tablename = data.get("Tablename");
        String exceptResult= data.get("Result");
        String raws = data.get("Raw");
        String[] rawArr = raws.split("\\+");

        Reporter.log("【用例名称】："+caseName);
        Reporter.log("【参数】: Tablename"+tablename+"  Raw:"+raws);

        List<String> orgs = Arrays.asList(rawArr);
        JSONObject obj;
        obj = c.table(tablename).insert(orgs).submit(Submit.SyncCond.db_success);
        System.out.println("insert result:" + obj);

        Reporter.log("【返回结果】"+ obj);
        String status = obj.getString("status");

        Reporter.log("【用例结果】: expected:"+exceptResult+"actual:"+status);
        Assert.assertEquals(status,exceptResult);
    }

    @DataProvider(name = "provideNumbers")
    public Object[][]  provieData() throws IOException
    {
        ExcelData data = new ExcelData("testData","insertTable");
        return data.getExcelData();
    }


}
