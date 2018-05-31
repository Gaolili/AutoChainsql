package Base;

import CommonFunction.Log;
import com.peersafe.chainsql.core.Chainsql;
import org.json.JSONObject;
import org.testng.annotations.BeforeSuite;

import java.util.logging.Level;

import static com.peersafe.chainsql.core.Chainsql.c;

public class BaseSetting {
    public static final Chainsql c = Chainsql.c;


    public static String rootAddress = "zHb9CJAWyB4zj91VRWn96DkukG4bwdtyTh";
    public static String rootSecret = "xnoPBzXtMeMyMHUVTgbuqAfg1SUTb";

//    public static String rootAddress = "zHyz3V6V3DZ2fYdb6AUc5WV4VKZP1pAEs9";
//    public static String rootSecret = "xxprJNCURq7J9BVpeox38CGq2nwLM";


    public static String sNewAccountId, sNewSecret;


    public void initChainsql(boolean isNeedReplaceAccount){
        c.connect("ws://10.100.0.101:6006");
        c.as(rootAddress, rootSecret);
        c.use(rootAddress);
//        //设置chainsql日志级别
        c.connection.client.logger.setLevel(Level.SEVERE);


        if (isNeedReplaceAccount){
            generateAccount();
            activateAccount(sNewAccountId);
            replaceAccount();
        }

    }

    public void generateAccount() {
        JSONObject obj = c.generateAddress();
        System.out.println("new account:" + obj);
        sNewAccountId = obj.getString("account_id");
        sNewSecret = obj.getString("secret");
    }

    // 激活账户
    public void activateAccount(String account) {
        //RPC是drop   java和node.js api是zxc
        JSONObject ret = c.pay(account, "123000");
        System.out.println("pay result:" + ret);
    }

    // 替换根账户，建表每次都使用不同账户
    public void  replaceAccount(){

        c.as(sNewAccountId, sNewSecret);
        c.use(sNewAccountId);

    }

}
