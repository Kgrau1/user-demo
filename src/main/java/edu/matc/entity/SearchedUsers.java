package edu.matc.entity;
import java.util.*;
public class SearchedUsers {

    private String searchTerm;
    private String searchType;
    private List<User> employees;
    private boolean employeesFound;

    public SearchedUsers() {
        employees = new ArrayList<>();
    }

    public void addFoundEmployee(User employee) {

        employees.add(employee);
        employeesFound = true;
    }

    /**
     * Sets search term
     * @param searchTerm the search term
     */
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Gets the search term
     * @return search term
     */
    public String getSearchTerm() {
        return searchTerm;
    }

    /**
     * Sets search type
     * @param searchType search type
     */
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    /**
     * Gets the search type
     * @return search type
     */
    public String getSearchType() {
        return searchType;
    }
    /**
     * Gets the results from the employees list.
     * @return The results from the employees list.
     */
    public List<User> getEmployees() {
        return employees;
    }
    /**
     * Sets the results from the employees list.
     * @param newEmployees The new results from the employees list.
     */
    public void setEmployees(List<User> newEmployees) {
        employees = newEmployees;
    }
    public boolean isEmployeesFound() {
        return employeesFound;
    }
    /**
     * Sets the employeesFound flag.
     * @param newEmployeesFound The new employeesFound flag.
     */
    public void setEmployeesFound(boolean newEmployeesFound) {
        employeesFound = newEmployeesFound;
    }
}