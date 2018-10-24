function f1Championship(input) {
    let teams = new Map();

    teams = fillTeams(input, teams);

    let sortedTeams = Array.from(teams.keys())
        .filter((currentTeam) => teams.get(currentTeam).size === 2)
        .sort((a, b) => [...teams.get(b).values()].reduce((acc, points) => acc + points) - [...teams.get(a).values()].reduce((acc, points) => acc + points))
        .slice(0, 3);

    for (let i = 0; i < sortedTeams.length; i++) {
        console.log(`${sortedTeams[i]}: ${[...teams.get(sortedTeams[i]).values()].reduce((acc, points) => acc + points)}`);

        let sortedPilots = [...teams.get(sortedTeams[i]).keys()]
            .sort((a, b) => teams.get(sortedTeams[i]).get(b) - teams.get(sortedTeams[i]).get(a))
            .forEach(currentPilot => {
                console.log(`-- ${currentPilot} -> ${teams.get(sortedTeams[i]).get(currentPilot)}`);
            });
    }
    function fillTeams(input, teams) {
        for (let i = 0; i < input.length; i++) {
            let [currentTeam, pilot, points] = input[i].split(" -> ");

            //if current team doesn't exists
            if (!teams.has(currentTeam)) {
                teams.set(currentTeam, new Map());
            }

            //if pilot doesn't exists in team and team has less than 2 pilots;
            if (!teams.get(currentTeam).has(pilot)) {

                //if pilot doesn't exists in other teams
                if (!checkPilotExistence(pilot, teams)) {
                    teams.get(currentTeam).set(pilot, Number(points));
                }

            } else {
                teams.get(currentTeam).set(pilot, teams.get(currentTeam).get(pilot) + Number(points));
            }
        }
        return teams;

        function checkPilotExistence(pilot, teams) {
            for (let currentTeam of teams) {
                if (currentTeam[1].has(pilot)) {
                    return true;
                }
            }
            return false;
        }

    }
}

f1Championship(["Ferrari -> Kimi Raikonnen -> 25",
    "Ferrari -> Sebastian Vettel -> 18",
    "Mercedes -> Lewis Hamilton -> 10",
    "Mercedes -> Valteri Bottas -> 8",
    "Red Bull -> Max Verstapen -> 6",
    "Red Bull -> Daniel Ricciardo -> 4"]
);