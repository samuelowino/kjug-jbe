
record Team(String name, int goals) implements Comparable<Team> {
    @Override
    public int compareTo(Team otherTeam) {
        // -1 : 0 : 1
        return this.name().compareTo(otherTeam.name());
    }
}
Comparator<Team> sortByName = new Comparator<Team>() {
    @Override
    public int compare(Team o1, Team o2) {
        return o1.name().compareTo(o2.name());
    }
};


class SortByNameComparator implements Comparator<Team> {
    @Override
    public int compare(Team team1, Team team2) {
        // -1 : 0 : 1
        return team1.name().compareTo(team2.name());
    }
}
void main() {
    List<Team> teams = new ArrayList<>();
    teams.add(new Team("USA",33));
    teams.add(new Team("Armenia",5));
    teams.add(new Team("Denmark",50));

    var it = teams.iterator();
    while (it.hasNext()) {
        var team = it.next();
        IO.println("""
                Name: %s : goals %d
                """.formatted(team.name(),team.goals()));
    }
    var comparator = new SortByNameComparator();
    Collections.sort(teams,sortByName);

    it = teams.iterator();
    while (it.hasNext()) {
        var team = it.next();
        IO.println("""
                Sorted : Name: %s : goals %d
                """.formatted(team.name(),team.goals()));
    }
}