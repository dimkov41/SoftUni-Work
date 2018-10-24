function f1Race(arr){
    let competitors = arr[0].split(" ").map(x => x.trim());

    for (let i = 1; i < arr.length; i++) {
        let [action,racer] = arr[i].split(" ").map(x => x.trim());

        competitors = doAnAction(action,racer,competitors);
    }

    console.log(competitors.join(" ~ "));


    function doAnAction(action,racer,competitors){
        let racerExists = competitors.includes(racer);

        switch (action) {
            case "Join":
                if(!racerExists){
                    competitors.push(racer);
                }
                break;
            case "Crash":
                if(racerExists){
                    let index = competitors.indexOf(racer);
                    competitors.splice(index,1);
                }
                break;
            case "Pit":
                if(racerExists){
                    let pilotPos = competitors.indexOf(racer);
                    let pilot = competitors[pilotPos];

                    let nexPilotPos = pilotPos + 1;

                    competitors[pilotPos] = competitors[nexPilotPos];
                    competitors[nexPilotPos] = pilot;
                }
                break;
            case "Overtake":
                if(racerExists){
                    let attakingPilotPos = competitors.indexOf(racer);

                    let pilotInFrontOfIndex = attakingPilotPos - 1;
                    let pilotInFrontOfName = competitors[pilotInFrontOfIndex];

                    if(pilotInFrontOfIndex >= 0){
                        competitors[pilotInFrontOfIndex] = competitors[attakingPilotPos];
                        competitors[attakingPilotPos] = pilotInFrontOfName;
                    }
                }
                break;
        }

        return competitors;
    }

}

f1Race(["Vetel",
    "Pit Hamilton",
    "Overtake Vetel",
    "Join Ricardo",
    "Crash Vetel",
    "Overtake Ricardo",
    "Overtake Ricardo",
    "Overtake Ricardo",
    "Crash Slavi"]);