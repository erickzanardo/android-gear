#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import com.androidgear.core.JavaGameLoop;
import ${package}.core.${GameClass};

public class Java${GameClass} {
    public static void main(String[] args) {
        JavaGameLoop gameLoop = new JavaGameLoop(new ${GameClass}());
        gameLoop.run();
    }
}
