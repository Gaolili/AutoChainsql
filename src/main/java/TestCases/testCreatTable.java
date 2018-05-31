package TestCases;

import Base.BaseSetting;
import CommonFunction.Assertion;
import CommonFunction.AssertionListener;
import CommonFunction.ExcelData;
import CommonFunction.Log;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;


import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.lang.String;

import org.json.JSONObject;
import org.json.JSONArray ;

import com.peersafe.chainsql.core.Chainsql;
import com.peersafe.chainsql.core.Submit.SyncCond;
import com.peersafe.chainsql.util.Util;
import com.peersafe.base.client.requests.*;
import com.peersafe.base.client.responses.*;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import sun.jvm.hotspot.utilities.AssertionFailure;

@Listeners({AssertionListener.class})
public class testCreatTable extends BaseSetting{

    @BeforeClass
    public void  setUP(){
       initChainsql(true);// 建表每次都需要更换账户，这样做将来做持续集成时方便，就不用更换表名了
    }

    @Test(dataProvider = "provideNumbers" )
    public void createTable(HashMap<String, String> data){
        String caseName= data.get("Casename");
        String tablename = data.get("Tablename");
        String exceptResult= data.get("Result");
        String raws = data.get("Raw");
        Boolean isConfidential = Boolean.getBoolean(data.get("isConfidential"));

        Reporter.log("【用例名称】："+caseName);
        Reporter.log("【参数】: Tablename"+tablename+"  Raw:"+raws);

        // 分割raw字段
        String[] rawArr = raws.split("\\+");

        List<String> args = Arrays.asList(rawArr);
        JSONObject obj;
        obj = c.createTable(tablename, args, isConfidential).submit(SyncCond.db_success);
        System.out.println("obj ="+obj);
        Reporter.log("【返回结果】"+ obj);
        String status = obj.getString("status");

        Reporter.log("【用例结果】: expected:"+exceptResult+"actual:"+status);
        Assert.assertEquals(status,exceptResult);
    }

    @DataProvider(name = "provideNumbers")
    public Object[][]  provieData() throws  IOException
    {
        ExcelData data = new ExcelData("testData","createTable");
        return data.getExcelData();
    }


}

