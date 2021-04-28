package de.lingen.modsim.KonkurrenzUmRessourcen.Database;

import javafx.beans.property.SimpleStringProperty;

/**
 * Class to use from a TableView.
 */
public class ResultsProject
{
    private SimpleStringProperty Project;

    /**
     *
     * @param Project a SimpleStringProperty which will be used in the TableView.
     */
    public ResultsProject(String Project)
    {
        this.Project = new SimpleStringProperty(Project);
    }

    /**
     * Getter and Setter for the TableView Data.
     * @return
     */
    public String getProject()
    {
        return Project.get();
    }

    public void setProject(String project)
    {
        this.Project.set(project);
    }

    public String getName()
    {
        return Project.get();
    }

}
