package pojo;

public class WorkspaceRoot {
	
	
	workspace workspace;
	
	public WorkspaceRoot() {}
	
	public workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(workspace workspace) {
		this.workspace = workspace;
	}



	public WorkspaceRoot(pojo.workspace workspace) {
	
		this.workspace = workspace;
	}

}
