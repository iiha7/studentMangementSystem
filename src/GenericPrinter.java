

public class GenericPrinter {

    public static <E> void print(E[] list){
        for(int i=0;i<list.length;i++){
            System.out.println(list[i]);
        }
    }
}
