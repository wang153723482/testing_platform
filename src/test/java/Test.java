import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.sqrt;

/**
 * Created by wangchao on 2017/2/13.
 */
public class Test {
    public static void main(String[] args){
        System.out.println(sqrt(6)/4 * 620 * 1 * sqrt(2));

        List<Object[]> splitUpNames = 
                Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());
        
        for(int i=0; i<splitUpNames.size(); i++){
            System.out.println( splitUpNames.get(i)[0] );
            System.out.println( splitUpNames.get(i)[1] );
            System.out.println("========");
        }
        
        String[] a  = new String[]{"a","b"};
        String[] a1  = new String[]{"a","b"};

        
        String[] JMETER_RUN_COMMOND = new String[]{"","","xxxx"};
        
        String LINUX_COMMOND = "/bin/sh";
        String LINUX_COMMOND_PARAM = "-C";
        
        String WIN_COMMOND = "cmd";
        String WIN_COMMOND_PARAM = "/c";
        
        if( true ){  //linux
            JMETER_RUN_COMMOND[0] = LINUX_COMMOND;
            JMETER_RUN_COMMOND[1] = LINUX_COMMOND_PARAM;
        }else{
            JMETER_RUN_COMMOND[0] = WIN_COMMOND;
            JMETER_RUN_COMMOND[1] = WIN_COMMOND_PARAM;
        }
        System.out.println( Arrays.toString(JMETER_RUN_COMMOND) );
        
    }
}
