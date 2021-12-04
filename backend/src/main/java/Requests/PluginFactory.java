package Requests;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Return a plugin instance by reflection
 */
public class PluginFactory {
    private static String environment = EnvironmentConfig.LocalGmail.name();

    public static Object getEmailPlugin() {
        // determine which class to use depending on environment; currently only has local
        String implementationName = null;
        if (environment == "LocalGmail") {
            implementationName = "UserManagement.LocalGmailGateway";
        }

        try {
            // get constructor of given class
            Constructor<?> constr = Class.forName(implementationName).getConstructor();
            // return dynamically instantiated instance of the class
            return constr.newInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
