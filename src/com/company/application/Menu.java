package com.company.application;

import com.company.dao.MemberDao;
import com.company.dao.TeamDao;
import com.company.entity.Member;
import com.company.entity.Team;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private TeamDao teamdao = new TeamDao();
    private MemberDao memberDao = new MemberDao();
    private Scanner scanner = new Scanner(System.in);
    private List<String> options = Arrays.asList(
            "Display Teams",
            "Display a Team",
            "Create Team",
            "Delete Team",
            "Create Team Member",
            "Delete Team Member");
    public void start() {
        String selection = "";

        do {
            printMenu();
            selection = scanner.nextLine();
            try {
                if (selection.equals("1")) {
                    displayTeams();
                } else if (selection.equals("2")) {
                    displayTeam();
                } else if (selection.equals("3")) {
                    createTeam();
                } else if (selection.equals("4")) {
                    deleteTeam();
                } else if (selection.equals("5")) {
                    createMember();
                } else if (selection.equals("6")) {
                    //deleteMember();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


            System.out.println("Press enter to continue....");
            scanner.nextLine();

        } while (!selection.equals("-1"));
    }

    private void createMember() throws SQLException {
        System.out.print("Enter first name of new member:");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name of new member:");
        String lastName = scanner.nextLine();
        System.out.print("Enter team id for new member:");
        int teamId = Integer.parseInt(scanner.nextLine());
        memberDao.createNewMember(firstName, lastName, teamId);

    }

    private void deleteTeam() throws SQLException {
        System.out.print("Enter team id to delete:");
        int id = Integer.parseInt(scanner.nextLine());
        teamdao.deleteTeamById(id);
    }

    private void createTeam() throws SQLException {
        System.out.print("Enter new team name:");
        String teamName = scanner.nextLine();
        teamdao.createNewTeam(teamName);
    }

    private void printMenu() {
        System.out.println("Select an Option:\n------------------------------------------------");
        for(int i = 0; i < options.size(); i++) {
            System.out.println(i + 1 + ") " + options.get(i));
        }
    }

    private void displayTeams() throws SQLException {
        List<Team> teams = teamdao.getTeams();
        for (Team team : teams) {
            System.out.println(team.getTeamId() + ": " + team.getName());
        }
    }

    private void displayTeam() throws SQLException {
        System.out.print("Enter team id: ");
        int id = Integer.parseInt(scanner.nextLine());
        Team team = teamdao.getTeamById(id);
        System.out.println(team.getTeamId() + ": " + team.getName());
        for (Member member: team.getMembers()) {
            System.out.println("\tMemberId: " + member.getMemberId() + " - Name: " + member.getFirstName() + " " + member.getLastName());
        }

    }
}
