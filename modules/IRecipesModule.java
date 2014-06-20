package vitzli.recipedumper.modules;

public interface IRecipesModule {

	IRecipeData[] getAllRecipes();
	String getPrefix();
	
	boolean isEnabled();
	
	public interface IRecipeData {
		String generateDescription();
	}
		
}
