package org.example.controllers;

import javax.validation.Valid;
import org.example.dao.PersonDAO;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//указывает что это класс контроллер
@RequestMapping("/people")//все методы будут начинаться с /people
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired//сохраняем бин этого класса через конструктор
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()//гет метод получения данных
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")//гет метод получения данных
    public String show(@PathVariable("id") int id, Model model) {//указываем что id в метода тот который указали в пути
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

//    @GetMapping("/new")
//    public String newPerson(Model model) {
//        model.addAttribute("person", new Person());
//        return "people/new";
//    }
    // тоже самое что и метод ниже

    @GetMapping("/new")//путь по которому переходим на этот метод
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()//метод добавления новых данных
    public String create(@ModelAttribute("person") @Valid Person person,
        BindingResult bindingResult) {//Валид подключает аннотации персона
        if (bindingResult.hasErrors())//если аннотации выдают ошибку
        {
            return "people/new";//возвращаемся на создание человека
        }

        personDAO.save(person);
        return "redirect:/people";//по завершению возврат на страницу пипл
    }

    @GetMapping("/{id}/edit")//гет метод получения данных, путь по которому переходим на этот метод
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")//метод изменения данных, путь по которому переходим на этот метод
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(person);
        return "redirect:/people";//по завершению возврат на страницу пипл
    }

    @DeleteMapping("/{id}")//метод удаления данных, путь по которому переходим на этот метод
    public String delete(@PathVariable("id") int id){
        personDAO.delete(personDAO.show(id));
        return "redirect:/people";//по завершению возврат на страницу пипл
    }
}
