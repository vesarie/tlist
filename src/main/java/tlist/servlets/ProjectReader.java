package tlist.servlets;

import javax.servlet.http.HttpServletRequest;
import tlist.models.Project;

public class ProjectReader extends FormReader<Project> {

    public ProjectReader(HttpServletRequest request) {
        super(request);
    }

    @Override
    public void update(Project project) {
        project.setName(getName());
    }

    public String getName() {
        String name = ServletUtil.getStringParameter(request, "name");
        if (name.isEmpty()) {
            setErrorMsg("name", "Name is required");
        }

        return name;
    }

}
