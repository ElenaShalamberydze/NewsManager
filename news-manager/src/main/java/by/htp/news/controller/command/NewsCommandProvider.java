package by.htp.news.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.htp.news.controller.command.impl.ChangeLocale;
import by.htp.news.controller.command.impl.CreateNews;
import by.htp.news.controller.command.impl.DeleteNews;
import by.htp.news.controller.command.impl.ShowAllNews;
import by.htp.news.controller.command.impl.ShowCreatePage;
import by.htp.news.controller.command.impl.ShowNews;
import by.htp.news.controller.command.impl.ShowNewsForEdit;
import by.htp.news.controller.command.impl.UpdateNews;

public class NewsCommandProvider {
	private Map<String, NewsCommand> commands = new HashMap<>();
	
	public NewsCommandProvider() {
		commands.put("create", new CreateNews());
		commands.put("show_news", new ShowNews());
		commands.put("update", new UpdateNews());
		commands.put("delete", new DeleteNews());
		commands.put("show_all", new ShowAllNews());
		commands.put("show_for_edit", new ShowNewsForEdit());
		commands.put("change_locale", new ChangeLocale());
		commands.put("to_create", new ShowCreatePage());
	}
	
	public NewsCommand getCommand(String commandName) {
		return commands.get(commandName);
	}
}

