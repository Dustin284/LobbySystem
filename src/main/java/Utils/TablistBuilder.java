package Utils;

import Manager.ConfigManager;
import Manager.RanksManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TablistBuilder {

    ConfigManager configManager = new ConfigManager();

    public void updateTablist(Player player) {
        String coloredHeader = ChatColor.translateAlternateColorCodes('&', configManager.getTablistHeader());
        player.setPlayerListHeader(coloredHeader);
        String coloredFooter = ChatColor.translateAlternateColorCodes('&', configManager.getTablistFooter());
        player.setPlayerListFooter(coloredFooter);

    }

    public void setAllPlayerTeams(){
        Bukkit.getOnlinePlayers().forEach(this::setPlayerTeams);
    }
    public void setPlayerTeams(Player player) {
        Scoreboard scoreboard = player.getScoreboard();
        Team Owner = scoreboard.getTeam("001Owner");
        if(Owner == null){
            Owner = scoreboard.registerNewTeam("001Owner");
        }
        Team Admin = scoreboard.getTeam("002Owner");
        if(Admin == null){
            Admin = scoreboard.registerNewTeam("002Owner");
        }
        Team Developer = scoreboard.getTeam("003Developer");
        if(Developer == null){
            Developer = scoreboard.registerNewTeam("003Developer");
        }
        Team Communitymanager = scoreboard.getTeam("004Communitymanager");
        if(Communitymanager == null){
            Communitymanager = scoreboard.registerNewTeam("004Communitymanager");
        }
        Team Moderatorplus = scoreboard.getTeam("005Moderatorplus");
        if(Moderatorplus == null){
            Moderatorplus = scoreboard.registerNewTeam("005Moderatorplus");
        }
        Team Moderator = scoreboard.getTeam("006Moderator");
        if(Moderator == null){
            Moderator = scoreboard.registerNewTeam("006Moderator");
        }
        Team Supporterplus = scoreboard.getTeam("007Supporterplus");
        if(Supporterplus == null){
            Supporterplus = scoreboard.registerNewTeam("007Supporterplus");
        }
        Team Supporter = scoreboard.getTeam("008Supporter");
        if(Supporter == null){
            Supporter = scoreboard.registerNewTeam("008Supporter");
        }
        Team Helper = scoreboard.getTeam("009Helper");
        if(Helper == null){
            Helper = scoreboard.registerNewTeam("009Helper");
        }
        Team Builder = scoreboard.getTeam("010Builder");
        if(Builder == null){
            Builder = scoreboard.registerNewTeam("010Builder");
        }
        Team Designer = scoreboard.getTeam("011Designer");
        if(Designer == null){
            Designer = scoreboard.registerNewTeam("011Designer");
        }
        Team VIP = scoreboard.getTeam("012VIP");
        if(VIP == null){
            VIP = scoreboard.registerNewTeam("012VIP");
        }
        Team Bloxplus = scoreboard.getTeam("013Bloxplus");
        if(Bloxplus == null){
            Bloxplus = scoreboard.registerNewTeam("013Bloxplus");
        }
        Team Blox = scoreboard.getTeam("014Blox");
        if(Blox == null){
            Blox = scoreboard.registerNewTeam("014Blox");
        }
        Team User = scoreboard.getTeam("015User");
        if(User == null){
            User = scoreboard.registerNewTeam("015User");
        }


        Owner.setPrefix(ChatColor.DARK_RED + "Owner " + ChatColor.RESET + " ✘ ");
        Owner.setColor(ChatColor.GRAY);
        Admin.setPrefix(ChatColor.RED + "Admin " + ChatColor.RESET + " ✘ ");
        Admin.setColor(ChatColor.GRAY);
        Developer.setPrefix(ChatColor.AQUA + "Developer " + ChatColor.RESET + " ✘ ");
        Developer.setColor(ChatColor.GRAY);
        Communitymanager.setPrefix(ChatColor.DARK_PURPLE + "Communitymanager " + ChatColor.RESET + " ✘ ");
        Communitymanager.setColor(ChatColor.GRAY);
        Moderatorplus.setPrefix(ChatColor.DARK_GREEN + "Moderator+ " + ChatColor.RESET + " ✘ ");
        Moderatorplus.setColor(ChatColor.GRAY);
        Moderator.setPrefix(ChatColor.GREEN + "Moderator " + ChatColor.RESET + " ✘ ");
        Moderator.setColor(ChatColor.GRAY);
        Supporterplus.setPrefix(ChatColor.DARK_AQUA + "Supporter+ " + ChatColor.RESET + " ✘ ");
        Supporterplus.setColor(ChatColor.GRAY);
        Supporter.setPrefix(ChatColor.AQUA + "Supporter " + ChatColor.RESET + " ✘ ");
        Supporter.setColor(ChatColor.GRAY);
        Helper.setPrefix(ChatColor.YELLOW + "Helper " + ChatColor.RESET + " ✘ ");
        Helper.setColor(ChatColor.GRAY);
        Builder.setPrefix(ChatColor.GOLD + "Builder " + ChatColor.RESET + " ✘ ");
        Builder.setColor(ChatColor.GRAY);
        Designer.setPrefix(ChatColor.LIGHT_PURPLE + "Designer " + ChatColor.RESET + " ✘ ");
        Designer.setColor(ChatColor.GRAY);
        VIP.setPrefix(ChatColor.DARK_PURPLE + "VIP " + ChatColor.RESET + " ✘ ");
        VIP.setColor(ChatColor.GRAY);
        Bloxplus.setPrefix(ChatColor.DARK_GREEN + "Blox+ " + ChatColor.RESET + " ✘ ");
        Bloxplus.setColor(ChatColor.GRAY);
        Blox.setPrefix(ChatColor.GREEN + "Blox " + ChatColor.RESET + " ✘ ");
        Blox.setColor(ChatColor.GRAY);
        User.setPrefix(ChatColor.GRAY + "User " + ChatColor.RESET + " ✘ ");
        User.setColor(ChatColor.GRAY);
        RanksManager ranksManager = new RanksManager();

        for(Player target : Bukkit.getOnlinePlayers()) {
            if (ranksManager.getCurrentRank(target.getUniqueId()).contains("Owner")) {
                Owner.addEntry(target.getName());
                continue;
            } else if (ranksManager.getCurrentRank(target.getUniqueId()).contains("Admin")) {
                Admin.addEntry(target.getName());
            } else if (ranksManager.getCurrentRank(target.getUniqueId()).contains("Developer")) {
                Developer.addEntry(target.getName());
            } else if (ranksManager.getCurrentRank(target.getUniqueId()).contains("Communitymanager")) {
                Communitymanager.addEntry(target.getName());
            } else if (ranksManager.getCurrentRank(target.getUniqueId()).contains("Moderator+")) {
                Moderatorplus.addEntry(target.getName());
            } else if (ranksManager.getCurrentRank(target.getUniqueId()).contains("Moderator")) {
                Moderator.addEntry(target.getName());
            } else if (ranksManager.getCurrentRank(target.getUniqueId()).contains("Supporter+")) {
                Supporterplus.addEntry(target.getName());
            } else if (ranksManager.getCurrentRank(target.getUniqueId()).contains("Supporter")) {
                Supporter.addEntry(target.getName());
            } else if (ranksManager.getCurrentRank(target.getUniqueId()).contains("Helper")) {
                Helper.addEntry(target.getName());
            } else if (ranksManager.getCurrentRank(target.getUniqueId()).contains("Builder")) {
                Builder.addEntry(target.getName());
            } else if (ranksManager.getCurrentRank(target.getUniqueId()).contains("Designer")) {
                Designer.addEntry(target.getName());
            } else if (ranksManager.getCurrentRank(target.getUniqueId()).contains("VIP")) {
                VIP.addEntry(target.getName());
            } else if (ranksManager.getCurrentRank(target.getUniqueId()).contains("Blox+")) {
                Bloxplus.addEntry(target.getName());
            } else if (ranksManager.getCurrentRank(target.getUniqueId()).contains("Blox")) {
                Blox.addEntry(target.getName());
            } else {
                User.addEntry(target.getName());
            }
        }

    }

}
