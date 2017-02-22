import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wangchao on 2017/2/13.
 */
public class Test {
    public static void main(String[] args){


        List<Object[]> splitUpNames = 
                Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());
        
        for(int i=0; i<splitUpNames.size(); i++){
            System.out.println( splitUpNames.get(i)[0] );
            System.out.println( splitUpNames.get(i)[1] );
            System.out.println("========");
        }
        
        
    }
}
