package com.etnlgravtnl.common.constant;

/**
 * Created by Administrator on 2016/7/15.
 */
public class SystemCommonConstants {

    public static String  getValue(StoreStatus storeStatus){

        return storeStatus.toString();
    }

    public  enum StoreStatus{
        NORMAL("normal"),
        CLOSE("close"),
        DELETE("delete");



        private StoreStatus(String value){
            this.type = value;
        }

        private String type;


        @Override
        public String toString() {
            return this.type;
        }

    }
}
