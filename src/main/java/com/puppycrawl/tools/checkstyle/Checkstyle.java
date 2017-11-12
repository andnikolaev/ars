package com.puppycrawl.tools.checkstyle;

import com.puppycrawl.tools.checkstyle.api.*;
import com.puppycrawl.tools.checkstyle.utils.CommonUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class Checkstyle {
    /**
     * Name for 'xml' format.
     */
    private static final String XML_FORMAT_NAME = "xml";

    /**
     * Name for 'plain' format.
     */
    private static final String PLAIN_FORMAT_NAME = "plain";

    /**
     * A key pointing to the create listener exception
     * message in the "messages.properties" file.
     */
    private static final String CREATE_LISTENER_EXCEPTION = "Main.createListener";

    public static String start(String sourceFilePath, String configurationPath) throws CheckstyleException, FileNotFoundException {
        final Properties props = System.getProperties();
        // create a configuration
        final ThreadModeSettings multiThreadModeSettings =
                new ThreadModeSettings(
                        1, 1);
        final ConfigurationLoader.IgnoredModulesOptions ignoredModulesOptions = ConfigurationLoader.IgnoredModulesOptions.OMIT;

        final Configuration config = ConfigurationLoader.loadConfiguration(
                "PrutzkowConfiguration.xml", new PropertiesExpander(props),
                ignoredModulesOptions, multiThreadModeSettings);

        // create a listener for output
        final AuditListener listener;

        listener = createListener("plain", null);


        // create RootModule object and run it
        final int errorCounter;
        final ClassLoader moduleClassLoader = Checker.class.getClassLoader();
        final RootModule rootModule = getRootModule(config.getName(), moduleClassLoader);
        String message;
        try {

            rootModule.setModuleClassLoader(moduleClassLoader);
            rootModule.configure(config);
            rootModule.addListener(listener);
            List<File> files = new LinkedList<>();
            files.add(new File(sourceFilePath));
            // run RootModule
            errorCounter = rootModule.process(files);
            message = rootModule.getMessage();
            System.out.println(message);
        } finally {
            rootModule.destroy();
        }
        System.out.println(errorCounter);
        return message;
    }

    private static AuditListener createListener(String format, String outputLocation) throws FileNotFoundException {
        // setup the output stream
        final OutputStream out;
        final AutomaticBean.OutputStreamOptions closeOutputStream;
        if (outputLocation == null) {
            out = System.out;
            closeOutputStream = AutomaticBean.OutputStreamOptions.NONE;
        } else {
            out = new FileOutputStream(outputLocation);
            closeOutputStream = AutomaticBean.OutputStreamOptions.CLOSE;
        }

        // setup a listener
        final AuditListener listener;
        if (XML_FORMAT_NAME.equals(format)) {
            listener = new XMLLogger(out, closeOutputStream);

        } else if (PLAIN_FORMAT_NAME.equals(format)) {
            listener = new DefaultLogger(out, closeOutputStream, out,
                    AutomaticBean.OutputStreamOptions.NONE);

        } else {
            if (closeOutputStream == AutomaticBean.OutputStreamOptions.CLOSE) {
                CommonUtils.close(out);
            }
            final LocalizedMessage outputFormatExceptionMessage = new LocalizedMessage(0,
                    Definitions.CHECKSTYLE_BUNDLE, CREATE_LISTENER_EXCEPTION,
                    new String[]{format, PLAIN_FORMAT_NAME, XML_FORMAT_NAME}, null,
                    Main.class, null);
            throw new IllegalStateException(outputFormatExceptionMessage.getMessage());
        }

        return listener;
    }

    /**
     * Creates a new instance of the root module that will control and run
     * Checkstyle.
     *
     * @param name              The name of the module. This will either be a short name that
     *                          will have to be found or the complete package name.
     * @param moduleClassLoader Class loader used to load the root module.
     * @return The new instance of the root module.
     * @throws CheckstyleException if no module can be instantiated from name
     */
    private static RootModule getRootModule(String name, ClassLoader moduleClassLoader)
            throws CheckstyleException {
        final ModuleFactory factory = new PackageObjectFactory(
                Checker.class.getPackage().getName(), moduleClassLoader);

        return (RootModule) factory.createModule(name);
    }

}

