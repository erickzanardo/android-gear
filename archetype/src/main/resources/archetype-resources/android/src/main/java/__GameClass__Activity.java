#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import com.androidgear.core.AbstractAndroidGearActivity;
import com.androidgear.core.Game;

import ${package}.core.${GameClass};

public class ${GameClass}Activity extends AbstractAndroidGearActivity {

    @Override
    public Game getGame() {
        return new ${GameClass}();
    }
}

