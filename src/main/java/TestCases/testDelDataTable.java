package TestCases;

import java.util.*;

/**
 * @Author:gaolili
 * @Date:Created in 下午6:11 ${Date}
 * @Description:
 */
public class testDelDataTable {
    public static void main(String[] args) {

        int[] arr = {3,1,2,4,0,5};
        HashMap <String,String>mm = new HashMap<>();

        for (int i=0;i<arr.length;i++){
            for (int j=i+1;j<arr.length;j++){
                //冒泡排序
                if (arr[i]>arr[j]){
                    int temp = arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }


                // 找到2数相加等于5的对
//                if (arr[i]+arr[j]==5){
//                    System.out.println(""+arr[i]+"+"+arr[j]+"=5");
//                     mm.put(""+arr[i],""+arr[j]);
//                  }
            }
        }


        int[] arr22 = {0,1,2,4,5,8};

      int value =  binarySearch(arr22,5);
        System.out.println();



    }


    public static int binarySearch(int[] arr,int searchValue){
        int low =0;
        int hight=arr.length-1;
        int mid=0;
        int search = 5;
        while (low<=hight){
            mid = (low+hight)/2;
            if (search<mid){
                hight = mid-1;
            }else if (search>mid){
                low=mid+1;
            }else {
                return mid;
            }


        }
        return -1;
    }
}
