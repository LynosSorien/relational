package com.djorquab.relational.relational.web.admin;

import com.djorquab.relational.relational.BackofficeConstants;
import com.djorquab.relational.relational.bo.ErrorJMSBO;
import com.djorquab.relational.relational.commons.PagedResult;
import com.djorquab.relational.relational.services.ErrorJMSService;
import com.djorquab.relational.relational.utils.BackofficeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/backoffice/admin")
public class AdminController {
    @Autowired
    private ErrorJMSService errorJMSService;

    @GetMapping("/jms")
    public ModelAndView jms() {
        PagedResult<ErrorJMSBO> pagedResult = errorJMSService.findAllPaged(0, 10);
        return BackofficeUtils.createModelAndViewWithTableDefinition("config/admin/jms", ErrorJMSBO.class,
                BackofficeConstants.TABLE_RESULT, pagedResult);
    }

    @GetMapping("/jms/error/doAgain/{errorId}")
    public ModelAndView doAgain(@PathVariable(name = "errorId") Long errorId) {
        errorJMSService.tryAgain(errorId);
        return jms();
    }

    @GetMapping("/jms/paging")
    public ModelAndView paging(
            @RequestParam(name = "page", defaultValue= "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = BackofficeConstants.ID, defaultValue = "") String id) {
        return BackofficeUtils.fragmentWithTableDefinition("fragments/tables :: pagedTable",
                ErrorJMSBO.class,
                "pagedEndpoint", "/backoffice/admin/jms/paging",
                BackofficeConstants.TABLE_RESULT, errorJMSService.findAllPaged(page-1, size),
                BackofficeConstants.ID, id,
                BackofficeConstants.GLOBAL_SUCCESS_MESSAGE, "Person deleted correctly");
    }

    @DeleteMapping("/jms/error")
    public ModelAndView delete(@RequestParam(name = "errorId") Long errorId, @RequestParam(name = BackofficeConstants.ID, required = false) String id) {
        errorJMSService.delete(errorId);
        return paging(1, 10, id);
    }
}
