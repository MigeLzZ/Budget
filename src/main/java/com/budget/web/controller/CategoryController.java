package com.budget.web.controller;

import com.budget.dao.entities.Category;
import com.budget.dao.entities.PlannedRecord;
import com.budget.dao.entities.Record;
import com.budget.dao.entities.User;
import com.budget.services.ICategoryService;
import com.budget.services.IPlannedRecordService;
import com.budget.services.IRecordService;
import com.budget.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "/categories")
public class CategoryController {

    private final ICategoryService categoryService;
    private final IRecordService recordService;
    private final IUserService userService;
    private final IPlannedRecordService plannedRecordService;

    @Autowired
    public CategoryController(ICategoryService categoryService, IRecordService recordService, IUserService userService, IPlannedRecordService plannedRecordService) {
        this.categoryService = categoryService;
        this.recordService = recordService;
        this.userService = userService;
        this.plannedRecordService = plannedRecordService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/*")
    public String categoriesPage(@AuthenticationPrincipal User user, ModelMap model){
        if(user == null){return "login";}

        int intMounth = Calendar.getInstance().getTime().getMonth();
        int year = Integer.parseInt(Calendar.getInstance().getTime().toString().substring(24));

        String mounth = null;
        for(RecordsController.Mounth m : RecordsController.Mounth.values()){
            if(intMounth == (m.ordinal())){
                mounth = m.name();
                break;
            }
        }

        List<Category> categories = categoryService.getStandartCategories();
        if(user.getUsersCategories() != null){
            categories.addAll(user.getUsersCategories());
        }

        int prevMounth = (intMounth == 0) ? (11) : (intMounth-1);
        int nextMounth = (intMounth == 11) ? (0) : (intMounth+1);
        model.addAttribute("prevMounth", prevMounth);
        model.addAttribute("nextMounth", nextMounth);
        model.addAttribute("categories", categories);
        model.addAttribute("mounth", mounth);
        model.addAttribute("intMounth", intMounth);
        model.addAttribute("year", year);
        //model.addAttribute("categories", categoryService.getAllCategories());
        return "Kategory_page";
    }

    /*@RequestMapping(method = RequestMethod.GET, value = "/getRecords/{category}")
    public String getRecordsByCategory(@AuthenticationPrincipal User user, HttpServletRequest request, ModelMap model, @PathVariable int category){
        if(user == null){return "login";}
        //model.addAttribute("categories", categoryService.getAllCategories());
        int intMounth = Calendar.getInstance().getTime().getMonth();
        int year = Integer.parseInt(Calendar.getInstance().getTime().toString().substring(24));

        String mounth = null;
        for(RecordsController.Mounth m : RecordsController.Mounth.values()){
            if(intMounth == (m.ordinal())){
                mounth = m.name();
                break;
            }
        }

        List<Category> categories = categoryService.getStandartCategories();
        /*if(user.getUsersCategories() != null){
            categories.addAll(user.getUsersCategories());
        }
        List<Record> records = new ArrayList<>();
        for(Record record : recordService.getRecordsByUserId(user.getId())){
            if(record.getCategory().getId() == category){
                records.add(record);
            }
        }
        Collections.sort(records, Record.getCompByDate());

        model.addAttribute("mounth", mounth);
        model.addAttribute("intMounth", intMounth);
        model.addAttribute("year", year);
        model.addAttribute("categories", categories);
        //model.addAttribute("records", records);
        model.addAttribute("message", " по категории " + categoryService.getCategoryByid(category).getType());
        return "Kategory_page";
    }*/

    @RequestMapping(method = RequestMethod.GET, value = "/getRecords/{category}/{year}/{intMounth}")
    public String getRecordsWhenChangeCategory(@AuthenticationPrincipal User user, ModelMap model, @PathVariable long category, @PathVariable int year, @PathVariable int intMounth){
        if(user == null){
            return "login";
        }

        List<Record> records = user.getRecordsByMounth(intMounth, year);
        List<Record> plannedRecords = user.getPlannedRecordsByMounth(intMounth, year);
        List<Record> allRecords = new ArrayList<>();
        allRecords.addAll(records);
        allRecords.addAll(plannedRecords);
        records.clear();
        for (Record record : allRecords){
            if(record.getCategory().getId() == category){
                records.add(record);
            }
        }
        Collections.sort(records, Record.getCompByDate());

        List<Category> categories = categoryService.getStandartCategories();
        if(user.getUsersCategories() != null){
            categories.addAll(user.getUsersCategories());
        }

        String mounth = null;
        for(RecordsController.Mounth m : RecordsController.Mounth.values()){
            if(intMounth == (m.ordinal())){
                mounth = m.name();
            }
        }

        int prevMounth = (intMounth == 0) ? (11) : (intMounth-1);
        int nextMounth = (intMounth == 11) ? (0) : (intMounth+1);
        model.addAttribute("prevMounth", prevMounth);
        model.addAttribute("nextMounth", nextMounth);
        model.addAttribute("records", records);
        model.addAttribute("categories", categories);
        model.addAttribute("mounth", mounth);
        model.addAttribute("intMounth", intMounth);
        model.addAttribute("year", year);
        model.addAttribute("categoryId", category);
        model.addAttribute("strCategory", categoryService.getCategoryByid(category).getType());

        return "Kategory_page";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getRecordsByMounth/{way}/{categoryId}/{year}/{intMounth}")
    public String getRecordsWhenChangeMounth(@AuthenticationPrincipal User user, ModelMap model, @PathVariable String way, @PathVariable long categoryId, @PathVariable int year, @PathVariable int intMounth){
        if(user == null){return "login";}

        String mounthStr = "";
        if(way.equals("prev") && intMounth == 11){
            year--;
        }
        if(way.equals("next") && intMounth == 0){
            year++;
        }
        int prevMounth = (intMounth == 0) ? (11) : (intMounth-1);
        int nextMounth = (intMounth == 11) ? (0) : (intMounth+1);

        List<Category> categories = categoryService.getStandartCategories();
        if(user.getUsersCategories() != null){
            categories.addAll(user.getUsersCategories());
        }

        for(RecordsController.Mounth m : RecordsController.Mounth.values()){
            if(intMounth == (m.ordinal())){
                mounthStr = m.name();
                break;
            }
        }

        if(categoryId == -1){
            model.addAttribute("categories", categories);
            model.addAttribute("year", year);
            model.addAttribute("prevMounth", prevMounth);
            model.addAttribute("nextMounth", nextMounth);
            model.addAttribute("mounth", mounthStr);
            model.addAttribute("records", null);
            model.addAttribute("intMounth", intMounth);
            return "Kategory_page";
        }
        List<Record> records = user.getRecordsByMounth(intMounth, year);
        List<Record> plannedRecords = user.getPlannedRecordsByMounth(intMounth, year);
        List<Record> allRecords = new ArrayList<>();
        allRecords.addAll(records);
        allRecords.addAll(plannedRecords);
        records.clear();

        for (Record record : allRecords){
            if(record.getCategory().getId() == categoryId){
                records.add(record);
            }
        }
        Collections.sort(records, Record.getCompByDate());

        model.addAttribute("categories", categories);
        model.addAttribute("year", year);
        model.addAttribute("prevMounth", prevMounth);
        model.addAttribute("nextMounth", nextMounth);
        model.addAttribute("mounth", mounthStr);
        model.addAttribute("records", records);
        model.addAttribute("intMounth", intMounth);
        model.addAttribute("strCategory", categoryService.getCategoryByid(categoryId).getType());
        return "Kategory_page";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addCategory")
    public String addCategory(@AuthenticationPrincipal User user, ModelMap model){
        if(user == null){return "login";}
        return "Add_category";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addCategory")
    public String addCategory(@AuthenticationPrincipal User user, HttpServletRequest request, ModelMap model){
        if(user == null){return "login";}
        //List<Category> categories = categoryService.getAllCategories();
        List<Category> categories = categoryService.getStandartCategories();
        if(user.getUsersCategories() != null){
            categories.addAll(user.getUsersCategories());
        }
        boolean exist = false;
        for (Category category : categories){
            if (category.getType().equals(request.getParameter("category"))){
                exist = true;
            }
        }
        if(exist){
            model.addAttribute("message", "Такая категория уже существует. Введите другую");
            return "Add_category";
        }else {
            Category category = new Category();
            category.setType(request.getParameter("category"));
            category.setUser(user);
            user.addUserCategory(category);
            categoryService.saveCategory(category);
            model.addAttribute("addedMessage", " Категория добавлена!");
            return "Add_category";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/changeCategory/{categoryId}")
    public String changeCategory(@AuthenticationPrincipal User user, HttpServletRequest request, ModelMap model, @PathVariable long categoryId){
        if(user == null){return "login";}

        Category category = categoryService.getCategoryByid(categoryId);
        boolean exist = false;
        for(Category category1 : categoryService.getStandartCategories()){
            if(category1.getId() == category.getId()){
                exist = true;
            }
        }

        if(exist){
            model.addAttribute("categoryChangeMessage", "Эту категорию нельзя менять!");
            return categoriesPage(user, model);
        }
        model.addAttribute("category", category);
        return "changeCategory";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/changeCategory")
    public String changeCategory(@AuthenticationPrincipal User user, ModelMap model, HttpServletRequest request){
        String delete = request.getParameter("clear_b");
        String change = request.getParameter("repair_b");
        long id = Long.parseLong(request.getParameter("id"));
        if(delete != null){
            Category category = categoryService.getCategoryByType("Без Категории", user.getId());
            Category deleteCategory = categoryService.getCategoryByid(id);
            for(Record record : deleteCategory.getRecords()){
                category.addRecord(record);
                deleteCategory.deleteRecordById(record.getId());
                record.setCategory(category);
                recordService.updateRecordCategoryId(record.getId(), category.getId());
                user.updateRecord(record);
            }
            for(PlannedRecord plannedRecord : deleteCategory.getPlannedRecords()){
                category.addPlannedRecords(plannedRecord);
                deleteCategory.deletePlannedRecordById(plannedRecord.getId());
                plannedRecord.setCategory(category);
                plannedRecordService.updatePlannedRecord(plannedRecord.getId(),category.getId());
                user.updatePlannedRecord(plannedRecord);
            }
            categoryService.saveCategory(category);
            user.deleteCategoryById(deleteCategory.getId());
            userService.saveCurrentUserWithDetailsUpdate(user);     ///////////////////////////////save user
            categoryService.saveCategory(deleteCategory);
            categoryService.deleteCategoryById(deleteCategory.getId());
            model.addAttribute("categoryChangeMessage", "Категория удалена!");
        }
        if(change != null){
            Category category = categoryService.getCategoryByid(id);
            if(categoryService.getCategoryByType(request.getParameter("categoryName"),user.getId()) != null){
                model.addAttribute("categoryExistMessage","такая категория уже существует!");
                model.addAttribute("category", category);
                return "changeCategory";
            }
            category.setType(request.getParameter("categoryName"));
            user.updateCategory(category);
            for(Record record : user.getRecords()){
                if(record.getCategory().getId() == category.getId()){
                    record.setCategory(category);
                }
            }

            for(PlannedRecord plannedRecord : user.getPlannedRecords()){
                if(plannedRecord.getCategory().getId() == category.getId()){
                    plannedRecord.setCategory(category);
                }
            }
            user.updateCategory(category);
            categoryService.saveCategory(category);
            model.addAttribute("categoryExistMessage","Категория изменена.");
            model.addAttribute("category", category);
            return "changeCategory";
        }

        return categoriesPage(user, model);
    }
}
