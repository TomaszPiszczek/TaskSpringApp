package FirstTest.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties("task")
public class TaskConfigurationProperties {
    private Template template;
    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public static class Template {
        private boolean allowMultipleTask;
        public boolean isAllowMultipleTask() {
            return allowMultipleTask;
        }
        public void setAllowMultipleTask(boolean allowMultipleTask) {
            this.allowMultipleTask = allowMultipleTask;
        }
    }
}
