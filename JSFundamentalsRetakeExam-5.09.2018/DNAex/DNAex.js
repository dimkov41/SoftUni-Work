function DNAex(input) {
    let allOrganisms = {};

    for (let i = 0; i < input.length; i++) {
        let regex = /([a-z!@#$?)]+)=([0-9]+)--([0-9]+)<<([a-z]+)/gm;

        let line = String(input[i]);

        if (line === "Stop!") break;

        let found = regex.exec(line);

        if (found !== null) {
            let genome = found[1].replace(/[!@#$?)]+/gm, "");
            let genomeLenght = +found[2];

            if (genome.length === genomeLenght) {
                let genomesCount = +found[3];
                let organism = found[4];

                if (![...Object.keys(allOrganisms)].includes(organism)) {
                    allOrganisms[organism] = +genomesCount;
                } else {
                    allOrganisms[organism] = allOrganisms[organism] + +genomesCount;
                }
            }
        }
    }

    Array.from(Object.keys(allOrganisms))
        .sort((a,b) => allOrganisms[b] - allOrganisms[a])
        .forEach(organismName => console.log(`${organismName} has genome size of ${allOrganisms[organismName]}`));

}

DNAex(["!@ab?si?di!a@=8--152<<human",
    "b!etu?la@=6--321<<dog",
    "!curtob@acter##ium$=14--230<<dog",
    "!some@thin@g##=9<<human",
    "Stop!"]);