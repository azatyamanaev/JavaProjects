package ru.itis.htmlgenerator;

import com.google.auto.service.AutoService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ru.itis.htmlgenerator.annotations.HtmlForm;
import ru.itis.htmlgenerator.annotations.HtmlInput;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"ru.itis.htmlgenerator.annotations"})
public class HtmlProcessor extends AbstractProcessor {

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Count of classes: " + annotatedElements.size());
        for (Element element : annotatedElements) {
            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = path.substring(1) + element.getSimpleName().toString() + ".html";
            Path out = Paths.get(path);
            FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
            freeMarkerConfigurer.setTemplateLoaderPath("classpath:templates");
            freeMarkerConfigurer.setDefaultEncoding("UTF-8");
            freemarker.template.Configuration configuration = freeMarkerConfigurer.getConfiguration();
            configuration.setEncoding(new Locale("en"), "utf-8");
            try {
                Map<String, Object> model = new HashMap();
                Template template = configuration.getTemplate("user_template.ftl");
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "PATH " + out.toString());
                BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile()));
                HtmlForm annotation = element.getAnnotation(HtmlForm.class);
                model.put("method", annotation.method());
                model.put("action", annotation.action());
                HtmlInput inputs[] = element.getAnnotationsByType(HtmlInput.class);
                model.put("inputs", inputs);
                String file = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
                writer.write(file);
                writer.close();
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            } catch (TemplateException e) {
                throw new IllegalStateException(e);
            }
        }
        return true;
    }
}

