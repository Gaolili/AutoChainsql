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
 * @Date:Created in 下午5:06 ${Date}
 * @Description:
 */
public class testRenameTable extends BaseSetting {

    @BeforeClass
    public void setUP(){
        initChainsql(false);
    }

    @Test(dataProvider = "providerData")
    public  void renameTableCase(HashMap<String,String>data){
        String caseName= data.get("Casename");
        String oldTableName = data.get("Tablename");
        String newTableName = data.get("NewTableName");
        String exceptResult= data.get("Result");

        Reporter.log("【用例名称】："+caseName);
        Reporter.log("【参数】: oldTablename:"+oldTableName+"newTableName:"+newTableName);

        JSONObject obj;
        obj = c.renameTable(oldTableName, newTableName).submit(Submit.SyncCond.db_success);

        Reporter.log("【返回结果】"+ obj);
        String status = obj.getString("status");

        Reporter.log("【用例结果】: expected:"+exceptResult+"actual:"+status);
        Assert.assertEquals(status,exceptResult);

        System.out.println("rename result:" + obj);


    }

    @DataProvider(name = "providerData")
    public Object[][]  provieData() throws IOException
    {
        ExcelData data = new ExcelData("testData","renameTable");
        return data.getExcelData();
    }


}
