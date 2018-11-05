package br.com.trustsystems.elfinder;

import org.apache.commons.fileupload.FileItemStream;

public class Test {
    public static void main(String[] args) {
        String a = "/a/v/c/d/";
//        String b="/a/b/c/d/";
//        int i = a.indexOf("/",b.length());
//        if(i<0){
//            System.out.println(a);
//        }else{
//            System.out.println(a.substring(0,a.indexOf("/",b.length())+1));
//        }
//if(a.endsWith("/")) {
//    String tmp = a.substring(0,a.length()-1);
//    System.out.println(tmp.substring(tmp.lastIndexOf("/")+1));
//}else{
//    System.out.println(a.substring( a.lastIndexOf("/")+1));
//}
//    }
//S3Utils.putObject("dev/tao.png");

        System.out.println(FileItemStream.class.getName());
    }
}
