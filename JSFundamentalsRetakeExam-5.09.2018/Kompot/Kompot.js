function kompot(input) {
    const peachWeight = 0.140;
    const plumWeight = 0.020;
    const cherryWeight = 0.009;

    const peachKgForOneKompot = peachWeight * 2.5;
    const plumKgForOneKompot = plumWeight * 10;
    const cherryKgForOneKompot = cherryWeight * 25;

    let kompot = ["peach", "plum", "cherry"];

    let kompotFruits = new Map();
    let rakiyaFruits = new Map();

    for (let i = 0; i < input.length; i++) {
        let [fruit, kg] = input[i].split(/\s* | ,/).map(x => x.trim());


        if (kompot.includes(fruit)) {
            //if fruit not exists in map
            if (!kompotFruits.has(fruit)) {
                kompotFruits.set(fruit, 0);
            }

            kompotFruits.set(fruit, +kompotFruits.get(fruit) + +kg);
        } else {

            if (!rakiyaFruits.has(fruit)) {
                rakiyaFruits.set(fruit, 0);
            }

            rakiyaFruits.set(fruit, +rakiyaFruits.get(fruit) + +kg);
        }
    }

    let totalKompots = {};

    for (let currentFruit of kompotFruits) {
        let [fruit, kg] = currentFruit;

        let kompots = 0;

        switch (fruit) {
            case "cherry":
                kompots = kg / cherryKgForOneKompot;
                break;
            case "peach":
                kompots = kg / peachKgForOneKompot;
                break;
            case "plum":
                kompots = kg / plumKgForOneKompot;
                break;
        }

        totalKompots[fruit] = Math.floor(kompots.toFixed(2));
    }

    let rakiya = +0;

    let a = 0;

    for (let currentFruit of rakiyaFruits) {
        let [fruit,kg] = currentFruit;
        rakiya += +kg/5;
    }

    console.log(`Cherry kompots: ${totalKompots.cherry | 0}`);
    console.log(`Peach kompots: ${totalKompots.peach | 0}`);
    console.log(`Plum kompots: ${totalKompots.plum | 0}`);
    console.log(`Rakiya liters: ${Number(rakiya).toFixed(2)}`);
}

kompot([ 'cherry 1.2',
    'peach 2.2',
    'plum 5.2',
    'peach 0.1',
    'cherry 0.2',
    'cherry 5.0',
    'plum 10',
    'cherry 20.0' ,
    'papaya 20' ]);
