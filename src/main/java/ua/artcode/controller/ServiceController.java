package ua.artcode.controller;

import com.mongodb.DuplicateKeyException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.artcode.model.common.User;
import ua.artcode.script.InitCodingBatTaskTrigger;
import ua.artcode.service.UserService;

import javax.validation.Valid;


/**
 * Created by Maxim on 17.03.2016.
 */

@Controller
@RequestMapping(value = "/service-menu")
public class ServiceController {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    private static final Logger LOG = Logger.getLogger(ServiceController.class);

    @RequestMapping(value = "/add-admin")
    public ModelAndView addAdmin() {
        ModelAndView mav = new ModelAndView("service/create-admin");
        mav.addObject("user", new User());
        return mav;
    }

    @RequestMapping(value = "/registration-admin", method = RequestMethod.POST)
    public ModelAndView registration(@Valid User user, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("service/create-admin");
        if (!result.hasErrors()) {
            try {
                userService.register(user);
                redirectAttributes.addFlashAttribute("message", messageSource.getMessage("registration.successful", null, LocaleContextHolder.getLocale()));
                mav.setViewName("redirect:/service-menu");
            } catch (DuplicateKeyException e) {
                mav.addObject("message", "Admin with name: " + user.getName() + " already exist!");
            }
        }
        return mav;
    }

    @RequestMapping(value = "/createDump")
    public ModelAndView createDumpOfDB(RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("redirect:/service-menu");
        try {
            InitCodingBatTaskTrigger.createDumpOfDataBase();
            redirectAttributes.addFlashAttribute("message", "Data base dump has been created successfully.");
        } catch (Exception e) {
            mav.setViewName("main/service-menu");
            mav.addObject("message", "Error. Dump has not been created!");
            //LOG.warn("Dump has not been created!");
        }
        return mav;
    }

    @RequestMapping(value = "/restoreDB")
    public ModelAndView restoreDB_FromDump(RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("redirect:/service-menu");
        try {
            InitCodingBatTaskTrigger.restoreDataBaseFromDump();
            redirectAttributes.addFlashAttribute("message", "Data base has been restored from dump successfully.");
        } catch (Exception e) {
            mav.setViewName("main/service-menu");
            mav.addObject("message", "Error. Database has not been restored!");
            //LOG.warn("Database has not been restored!");
        }
        return mav;
    }
}
