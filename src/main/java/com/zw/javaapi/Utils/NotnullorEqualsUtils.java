package com.zw.javaapi.Utils;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NotnullorEqualsUtils<T> {

//    ①判断字符串是不是null或者空
//    ②判断两个字符串是不是相同的
    public Boolean NullorEquals(String value,String value1){
        Boolean message=true;

        if(value!=null&&!value.isEmpty()){
            if(value1!=null&&!value1.isEmpty()){
                if(!value.equals(value1)){
                    message=false;
                }
            }
        }else{
            message=false;
        }
        return message;
    }

//    判断list是不是空
    public Boolean ListNull(List list){
        Boolean message=true;

        if(list!=null){
            if(list.size()==0){
                message=false;
            }
        }else{
            message=false;
        }
        return message;
    }

    //比较两个list
    //取出存在menuOneList中，但不存在resourceList中的数据，type=0是差异的，type=1是相同部分的
    public  List listCompare(List<T> menuOneList, List<T> resourceList, Integer type) {

        List<T> List = new ArrayList<T>();
        Map<T,Integer> map = new HashMap<T,Integer>(resourceList.size());

        if(ListNull(menuOneList)&&(ListNull(resourceList))){

            if(menuOneList.size()<resourceList.size()){
                for(T resource : resourceList){
                    map.put(resource,1);
                }
                if(type==0){
                    for(T resource1 : menuOneList){
                        if(map.get(resource1)==null){
                            List.add(resource1);
                        }
                    }
                }else{
                    for(T resource1 : menuOneList){
                        if(map.get(resource1)!=null){
                            List.add(resource1);
                        }
                    }
                }

            }else{
                for(T resource : menuOneList){
                    map.put(resource,1);
                }
                if(type==0){
                    for(T resource1 : resourceList){
                        if(map.get(resource1)==null){
                            List.add(resource1);
                        }
                    }
                }else{
                    for(T resource1 : resourceList){
                        if(map.get(resource1)!=null){
                            List.add(resource1);
                        }
                    }
                }
            }

        }

        return List;
    }

}
