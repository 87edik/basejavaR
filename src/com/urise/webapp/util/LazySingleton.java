package com.urise.webapp.util;

public class LazySingleton {
    volatile private static LazySingleton INSTANSE;

    public LazySingleton() {
    }

    private static class LazySingletonHolder{
        private static final LazySingleton INTSTANSE = new LazySingleton();
    }
    public static LazySingleton getInstance() {
        return LazySingletonHolder.INTSTANSE;
    }
//    public static LazySingleton getInstance() {
//        if (INSTANSE == null) {
//            synchronized (LazySingleton.class) {
//                if (INSTANSE == null) {
//                    INSTANSE = new LazySingleton();
//                }
//            }
//
//        }
//        return INSTANSE;
//    }


}
