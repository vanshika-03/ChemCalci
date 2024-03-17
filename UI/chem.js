
function getNormality() {
    console.log(document.getElementById('normalitySoluteWeight').value)

    num = ((1000 * (document.getElementById('normalitySoluteWeight').value)))
    den = (document.getElementById('normalityWeight').value) * (document.getElementById('normalitySolution').value)
    console.log(num)
    console.log(den)
    document.getElementById('normalityResult').value = num / den + " ml"
}
function getNormalityLiquid() {
    console.log(document.getElementById('normalityMolarity').value)
    console.log(document.getElementById('normalityNfactor').value)
    console.log(document.getElementById('normalityMolarity').value * document.getElementById('normalityNfactor').value)
    document.getElementById('normalityLiquidResult').value = (document.getElementById('normalityMolarity').value * document.getElementById('normalityNfactor').value) + " ml"

}
function getStrength() {
    document.getElementById('strengthResult').value = document.getElementById('strengthSoluteWeight').value / document.getElementById('molalitySolutionVolume').value + " g/L"
}
async function getMolarityOrMolality(key) {
    if (key == "molarityLiquid")
        url = "http://localhost:8443/chem/molarity"
    else
        url = "http://localhost:8443/chem/" + key

    if (key == "molarity") {
        document.getElementById('molaritySolution').value
        data = {
            solution: document.getElementById('molaritySolution').value,
            solutionWeight: document.getElementById('molaritySolutionWeight').value,
            solute: document.getElementById('molaritySolute').value,
            soluteWeight: document.getElementById('molaritySoluteWeight').value,
            type: "solid"
        }
    }
    else if (key == "molarityLiquid") {
        document.getElementById('molaritySolution').value
        data = {
            solution: document.getElementById('molarityLiquidSolution').value,
            solutionWeight: document.getElementById('molarityLiquidSolutionVolume').value,
            solute: document.getElementById('molarityLiquidSolute').value,
            soluteWeight: document.getElementById('molarityLiquidSoluteVolume').value,
            specificGravity: document.getElementById('molaritySpecificGravity').value,
            type: "liquid"
        }
    }
    else if (key == "molality") {
        data = {
            solvent: document.getElementById('molalitySolution').value,
            solventWeight: document.getElementById('molalitySolutionWeight').value,
            solute: document.getElementById('molalitySolute').value,
            soluteWeight: document.getElementById('molalitySoluteWeight').value

        }
    }
    await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            console.log(response.status)
            if (response.status == 204) {
                if (key == "molarity") {
                    document.getElementById('molarityResult').value = ""
                }
                else {
                    document.getElementById('molalityResult').value = ""
                }
                alert("compound is not valid")
            }
            return response.json()
        })
        .then(response => {
            // if(response.)
            console.log(response.response)
            console.log(response.response[0])
            if (key == "molarity") {
                document.getElementById('molarityResult').value = response.response[0] + " mol/L"
            }
            else if (key == "molarityLiquid") {
                document.getElementById('molarityLiquidResult').value = response.response[0] + " mol/L"

            }
            else {
                document.getElementById('molalityResult').value = response.response[0] + " mol/kg"
            }
            // return response.response
        })
        .catch(e => {
            console.error(e.message)
        });

}

async function getMoleFraction() {
    url = "http://localhost:8443/chem/molefraction"
    console.log(document.getElementById('molefractionSolute').value)
    data = {
        solvent: document.getElementById('molefractionSolvent').value,
        solventWeight: document.getElementById('molefractionSolventWeight').value,
        solute: document.getElementById('molefractionSolute').value,
        soluteWeight: document.getElementById('molefractionSoluteWeight').value
    }
    await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.status == 204) {
                document.getElementById('molefractionResultSolute').value = ""
                document.getElementById('molefractionResultSolvent').value = ""
                alert("compound is not valid")
            }
            else
                return response.json()
        })
        .then(response => {
            console.log(response.response)
            console.log(response.response[0])
            document.getElementById('molefractionResultSolute').value = response.response[0]
            document.getElementById('molefractionResultSolvent').value = response.response[1]
            // return response.response
        })
        .catch(e => {
            console.error(e.message)
        });
}

async function getMolarWeight() {
    url = "http://localhost:8443/chem/molarweight"
    console.log(document.getElementById('molefractionSolute').value)
    data = {
        compound: document.getElementById('molarweightCompund').value
    }
    await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.status == 204) {
                document.getElementById('molarweightResult').value = ""
                alert("compound is not valid")
            }
            else
                return response.json()
        })
        .then(response => {
            console.log(response.response)
            console.log(response.response[0])
            document.getElementById('molarweightResult').value = response.response[0] + " grams/mole"
            // return response.response
        })
        .catch(e => {
            console.error(e.message)
        });
}
function openTab(tabName) {
    var i, x;
    x = document.getElementsByClassName("containerTab");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    tabName.forEach(tab =>
        document.getElementById(tab).style.display = "inline");
}
function selectfunc(func) {
    // document.getElementById("solute").value = "";
    // document.getElementById("solvent").value = "";
    // document.getElementById("soluteWeight").value = "";
    // document.getElementById("solventWeight").value = "";
    // document.getElementById("solution").value = "";
    // document.getElementById("solutionWeight").value = "";
    // document.getElementById(func).style.visibility="visible";
    document.getElementById(func).style.borderBottomColor = 'black'
    document.getElementById("menu".concat(func)).style.color = 'black'
    if (func == "Molarity") {
        document.getElementById("menuMolarity").style.color = 'black'
        document.getElementById("menuMolality").style.color = 'gray'
        document.getElementById("menuMolarWeight").style.color = 'gray'
        document.getElementById("menuNormality").style.color = 'gray'
        document.getElementById("menuStrength").style.color = 'gray'
        document.getElementById("menuMolefraction").style.color = 'gray'
        document.getElementById("menuMolality").style.borderBottomColor = 'gray'
        document.getElementById("menuMolarWeight").style.borderBottomColor = 'gray'
        document.getElementById("menuNormality").style.borderBottomColor = 'gray'
        document.getElementById("menuStrength").style.borderBottomColor = 'gray'
        document.getElementById("menuMolefraction").style.borderBottomColor = 'gray'
        // document.getElementById("Molality").style.visibility="hidden";
        // document.getElementById("MolarWeight").style.visibility="hidden";
        // document.getElementById("Normality").style.visibility="hidden";
        // document.getElementById("Strength").style.visibility="hidden";
        // document.getElementById("Molefraction").style.visibility="hidden";
    }
    else if (func == "MolarityLiquid") {
        document.getElementById("menuMolarity").style.color = 'black'
        document.getElementById("menuMolality").style.color = 'gray'
        document.getElementById("menuMolarWeight").style.color = 'gray'
        document.getElementById("menuNormality").style.color = 'gray'
        document.getElementById("menuStrength").style.color = 'gray'
        document.getElementById("menuMolefraction").style.color = 'gray'
        document.getElementById("menuMolality").style.borderBottomColor = 'gray'
        document.getElementById("menuMolarWeight").style.borderBottomColor = 'gray'
        document.getElementById("menuNormality").style.borderBottomColor = 'gray'
        document.getElementById("menuStrength").style.borderBottomColor = 'gray'
        document.getElementById("menuMolefraction").style.borderBottomColor = 'gray'
        // document.getElementById("Molality").style.visibility="hidden";
        // document.getElementById("MolarWeight").style.visibility="hidden";
        // document.getElementById("Normality").style.visibility="hidden";
        // document.getElementById("Strength").style.visibility="hidden";
        // document.getElementById("Molefraction").style.visibility="hidden";
    }
    else if (func == "Molality") {
        document.getElementById("menuMolality").style.color = 'black'
        document.getElementById("menuMolarity").style.color = 'gray'
        document.getElementById("menuMolarWeight").style.color = 'gray'
        document.getElementById("menuNormality").style.color = 'gray'
        document.getElementById("menuStrength").style.color = 'gray'
        document.getElementById("menuMolefraction").style.color = 'gray'
        document.getElementById("menuMolarity").style.borderBottomColor = 'gray'
        document.getElementById("menuMolarWeight").style.borderBottomColor = 'gray'
        document.getElementById("menuNormality").style.borderBottomColor = 'gray'
        document.getElementById("menuStrength").style.borderBottomColor = 'gray'
        document.getElementById("menuMolefraction").style.borderBottomColor = 'gray'
        // document.getElementById("Molarity").style.visibility="hidden";
        // document.getElementById("MolarWeight").style.visibility="hidden";
        // document.getElementById("Normality").style.visibility="hidden";
        // document.getElementById("Strength").style.visibility="hidden";
        // document.getElementById("Molefraction").style.visibility="hidden";
    }
    else if (func == "Molefraction") {
        document.getElementById("menuMolefraction").style.color = 'black'
        document.getElementById("menuMolarity").style.color = 'gray'
        document.getElementById("menuMolarWeight").style.color = 'gray'
        document.getElementById("menuNormality").style.color = 'gray'
        document.getElementById("menuStrength").style.color = 'gray'
        document.getElementById("menuMolality").style.color = 'gray'
        document.getElementById("menuMolarity").style.borderBottomColor = 'gray'
        document.getElementById("menuMolarWeight").style.borderBottomColor = 'gray'
        document.getElementById("menuNormality").style.borderBottomColor = 'gray'
        document.getElementById("menuStrength").style.borderBottomColor = 'gray'
        document.getElementById("menuMolality").style.borderBottomColor = 'gray'
        // document.getElementById("Molarity").style.visibility="hidden";
        // document.getElementById("MolarWeight").style.visibility="hidden";
        // document.getElementById("Normality").style.visibility="hidden";
        // document.getElementById("Strength").style.visibility="hidden";
        // document.getElementById("Molality").style.visibility="hidden";
    }
    else if (func == "MolarWeight") {
        document.getElementById("menuMolarWeight").style.color = 'black'
        document.getElementById("menuMolarity").style.color = 'gray'
        document.getElementById("menuMolefraction").style.color = 'gray'
        document.getElementById("menuNormality").style.color = 'gray'
        document.getElementById("menuStrength").style.color = 'gray'
        document.getElementById("menuMolality").style.color = 'gray'
        document.getElementById("menuMolarity").style.borderBottomColor = 'gray'
        document.getElementById("menuMolefraction").style.borderBottomColor = 'gray'
        document.getElementById("menuNormality").style.borderBottomColor = 'gray'
        document.getElementById("menuStrength").style.borderBottomColor = 'gray'
        document.getElementById("menuMolality").style.borderBottomColor = 'gray'
        // document.getElementById("Molarity").style.visibility="hidden";
        // document.getElementById("Normality").style.visibility="hidden";
        // document.getElementById("Strength").style.visibility="hidden";
        // document.getElementById("Molality").style.visibility="hidden";
        // document.getElementById("Molefraction").style.visibility="hidden";
    }
    else if (func == "Normality") {
        document.getElementById("menuNormality").style.borderBottomColor = "black";
        document.getElementById("menuMolarity").style.color = 'gray'
        document.getElementById("menuMolefraction").style.color = 'gray'
        document.getElementById("menuMolarWeight").style.color = 'gray'
        document.getElementById("menuStrength").style.color = 'gray'
        document.getElementById("menuMolality").style.color = 'gray'
        document.getElementById("menuMolarity").style.borderBottomColor = 'gray'
        document.getElementById("menuMolefraction").style.borderBottomColor = 'gray'
        document.getElementById("menuMolarWeight").style.borderBottomColor = 'gray'
        document.getElementById("menuStrength").style.borderBottomColor = 'gray'
        document.getElementById("menuMolality").style.borderBottomColor = 'gray'
        // document.getElementById("Molarity").style.visibility="hidden";
        // document.getElementById("MolarWeight").style.visibility="hidden";
        // document.getElementById("Strength").style.visibility="hidden";
        // document.getElementById("Molality").style.visibility="hidden";
        // document.getElementById("Molefraction").style.visibility="hidden";
    }
    else if (func == "Strength") {
        document.getElementById("menuStrength").style.color = 'black'
        document.getElementById("menuMolarity").style.color = 'gray'
        document.getElementById("menuMolefraction").style.color = 'gray'
        document.getElementById("menuNormality").style.color = 'gray'
        document.getElementById("menuMolarWeight").style.color = 'gray'
        document.getElementById("menuMolality").style.color = 'gray'
        document.getElementById("menuMolarity").style.borderBottomColor = 'gray'
        document.getElementById("menuMolefraction").style.borderBottomColor = 'gray'
        document.getElementById("menuNormality").style.borderBottomColor = 'gray'
        document.getElementById("menuMolarWeight").style.borderBottomColor = 'gray'
        document.getElementById("menuMolality").style.borderBottomColor = 'gray'
        // document.getElementById("Molarity").style.visibility="hidden";
        // document.getElementById("MolarWeight").style.visibility="hidden";
        // document.getElementById("Normality").style.visibility="hidden";
        // document.getElementById("Molality").style.visibility="hidden";
        // document.getElementById("Molefraction").style.visibility="hidden";
    }
}


