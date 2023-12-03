package info.developia.lib.util;

public class Launcher {
    public static void main(String[] args) {
//        var none = Try.of(() -> print("Hello world!!"));
//        print("var none:" + none.toString());
//        var str = Try.of(() -> "Hello world!!");
//        print("var str:" + str.toString());
//        var error = Try.of(() -> print("hola".split("_")[9]));
//        print("var error:" + error.toString());
//        print("var error fallback:" + error.getOr("salvados!!!"));
//        try {
//            str.get().getOrFailWith(new RuntimeException("Que no se pinte este error"));
//            error.getOrFailWith(new RuntimeException("Cual ha sido nuestro error"));
//        } catch (Exception e) {
//            print("Bienvenidos al catch!! ha pasado " + e);
//        }
//
//
//        var result = Try.of(() -> "Hello world!!");
//        print("var result:" + result.get());
//        print("var result fail:" + result.get().fail());

//        var resultAsterisk = Try.ofs(() -> queryDataBase("select * from database"));
//        print("Result * is " + resultAsterisk.get());
//        var resultD_Id = Try.ofs(() -> queryDataBase("select d_id from database")).get();
//        print("Result d_id is " + resultD_Id);
//
//        var recoveredResult = resultAsterisk.recover((a) -> "recovered result" + a);
//        print("Recovered Result * is " + recoveredResult.get());

//        if (resultAsterisk instanceof Result.Success<?>){
//            System.out.println("instanceof " + ((Result.Success<?>)resultAsterisk).value());
//        } else{
//            System.out.println("instanceof " + ((Result.Failure)resultAsterisk).error());
//        }
//        switch (Try.of(() -> queryDataBase("select d_id from database"))) {
//            case Result.Success s -> System.out.println(s.value());
//            case Result.Failure e -> System.out.println(e);
//        }
    }

    private static void print(String message) {
        System.out.println(message);
    }

    private static String queryDataBase(String sql) {
        print("Querying database " + sql);
        if (sql.contains("*"))
            throw new RuntimeException("Cannot execute sql query: " + sql);
        else
            return "1; you get id!!";
    }
}
