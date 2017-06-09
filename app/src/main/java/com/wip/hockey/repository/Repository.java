package com.wip.hockey.repository;

import com.wip.hockey.R;
import com.wip.hockey.model.Category;
import com.wip.hockey.model.Division;
import com.wip.hockey.model.Match;
import com.wip.hockey.model.SubDivision;
import com.wip.hockey.model.Team;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by djorda on 09/06/2017.
 */

public class Repository {

    private ArrayList<Division> divisions;

    public Repository() {
        createDivisions();
    }

    private void createDivisions() {
        divisions = new ArrayList();
        String[] names = getDivisionNames();

        for (int i = 0 ; i < names.length ; i++){
            Division division = new Division();
            division.setName(names[i]);
            division.setSubDivision(createSubDivisions(division));
            divisions.add(division);
        }
    }

    private ArrayList<SubDivision> createSubDivisions(Division division) {
        ArrayList<SubDivision> subDivisions = new ArrayList<>();
        String[] names = getSubDivisionNames(division.getName());

        for (int i = 0 ; i < names.length ; i++){
            SubDivision subDivision = new SubDivision();
            subDivision.setName(names[i]);
            subDivision.setCategories(createCategories());
            subDivisions.add(subDivision);
        }
        return subDivisions;
    }

    private ArrayList<Category> createCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        String[] names = getCategoryNames();

        for (int i = 0 ; i < names.length ; i++){
            Category category  = new Category();
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            long time = cal.getTimeInMillis();
            category.setId(time);
            category.setName(names[i]);
            category.setMatch(createMatches());
            categories.add(category);
        }
        return categories;
    }

    public ArrayList<Match> createMatches(){
        ArrayList<Match> matches = new ArrayList<>();
        int[] images = getMatchesImages();
        String[] names = getMatchesNames();

        for (int i = 0 ; i < images.length ; i+=2){
            Match match = new Match();

            Team localTeam = new Team();
            localTeam.setLogo(images[i]);
            localTeam.setName(names[i]);

            Team enemyTeam = new Team();
            enemyTeam.setLogo(images[i+1]);
            enemyTeam.setName(names[i+1]);

            match.setEnemyTeam(enemyTeam);
            match.setLocalTeam(localTeam);

            matches.add(match);
        }
        return matches;
    }

    private String[] getDivisionNames() {
        String[] names = {
                "A",
                "B",
                "C",
                "D"
        };
        return names;
    }

    private String[] getSubDivisionNames(String division) {

        String[] names = {
                division + "1",
                division + "2",
                division + "3"
        };

        return names;
    }


    private String[] getCategoryNames() {

        String[] names = {
                "Primera",
                "Intermedia"
        };

        return names;
    }

    private int[] getMatchesImages() {

        int[] images = {
                R.drawable.regatas,
                R.drawable.san_luis,
                R.drawable.alem_quilmes,
                R.drawable.bacrc

        };

        return images;
    }

    private String[] getMatchesNames() {

        String[] names = {
                "REGATAS DE AVELLANEDA",
                "SAN LUIS",
                "ALEMAN DE QUILMES",
                "BACRC"
        };

        return names;
    }

    public ArrayList<Division> getDivisions() {
        return divisions;
    }

    public ArrayList<SubDivision> getSubDivisions(Division division){
        for (Division div: divisions ) {
            if(div==division){
                return div.getSubDivision();
            }
        }
        return new ArrayList();
    }

    public ArrayList<Category> getCategories(SubDivision subDivision){
        for (Division div: divisions ) {
            for (SubDivision sub : div.getSubDivision()){
                if (sub == subDivision) {
                    return sub.getCategories();
                }
            }
        }
        return new ArrayList();
    }

    public ArrayList<Match> getMatches(Category category){
        for (Division div: divisions ) {
            for (SubDivision sub : div.getSubDivision()){
                for (Category cat : sub.getCategories()){
                    if (cat == category) {
                        return cat.getMatch();
                    }
                }
            }
        }
        return new ArrayList();
    }

    public Category removeFavorite(long id) {
        for (Division div: divisions ) {
            for (SubDivision sub : div.getSubDivision()){
                for (Category cat : sub.getCategories()){
                    if (cat.getId() == id) {
                        return cat;
                    }
                }
            }
        }
        return null;
    }
}
