/*
* ${mocker.headDesc}
*/
package ${mocker.packageName};

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.ArgumentMatchers.any;
<#list mocker.mockerFields as mockerFields>
import ${mockerFields.typeFullName};
</#list>

/**
* ${mocker.testClassName}
*
* @author ${mocker.author!''}
* @date ${mocker.date!''}
*/
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class ${mocker.testClassName} {

    @InjectMocks
    private ${mocker.className} ${mocker.className};

<#list mocker.mockerFields as mockerFields>
    @Mock
    private ${mockerFields.typeName} ${mockerFields.fieldName};
</#list>

<#list mocker.mockerMethods as mockerMethods>
    @Test
    public void ${mockerMethods.methodName}(){
        // 1.set up

        // 2.run test

        // 3.verify result
    };
</#list>
}