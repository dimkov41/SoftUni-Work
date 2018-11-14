function paymentProcessor() {
    class PaymentProcessor {
        constructor(options) {
            this.options = {};
            this.setOptions(options);

            this.payments = [];
        }

        registerPayment(id, name, type, value) {
            let idValidation = typeof id === "string" && id.length > 0;
            let nameValidation = typeof name === "string" && name.length > 0;
            let valueValidation = typeof value === "number";
            let typeValidation = this.options.types.includes(type);
            let idNotExists = (() => {
                for (let i = 0; i < this.payments.length; i++) {
                    let currentPayment = this.payments[i];
                    if (currentPayment.id === id) {
                        return false;
                    }
                }

                return true;
            })();

            if (idValidation
                && nameValidation
                && valueValidation
                && typeValidation
                && idNotExists) {

                this.payments.push({
                    id,
                    name,
                    type,
                    value: value.toFixed(this.options.precision)
                })

            } else {
                throw new Error("invalid type");
            }
        }

        deletePayment(id) {
            let idExists = (() => {
                for (let i = 0; i < this.payments.length; i++) {
                    let current = this.payments[i];
                    if (current.id === id) {
                        return [true, i];
                    }
                }
                return false;
            })();

            if (idExists[0]) {
                let index = idExists[1];
                if (index > -1) {
                    this.payments.splice(index, 1);
                }

            } else {
                throw new Error("ID not found");
            }
        }

        //Details about payment ID: E028
        // - Name: Rare-earth elements
        // - Type: material
        // - Value: 8000.00
        get(id) {
            let idExists = (() => {
                for (let i = 0; i < this.payments.length; i++) {
                    let current = this.payments[i];
                    if (current.id === id) {
                        return [true, i];
                    }
                }
                return false;
            })();

            if (idExists[0]) {
                let currentPayment = this.payments[idExists[1]];

                let output = [
                    `Details about payment ID: ${currentPayment.id}`,
                    ` - Name: ${currentPayment.name}`,
                    ` - Type: ${currentPayment.type}`,
                    ` - Value: ${currentPayment.value}`
                ];

                return output.join("\n");

            } else {
                throw new Error("ID not found");
            }
        }

        setOptions(options) {
            if (options === undefined) {
                this.options.types = ["service", "product", "other"];
                this.options.precision = 2;
            } else {
                this.options.types = options.types || ["service", "product", "other"];
                this.options.precision = options.precision || 2;
            }
        }

        toString() {
            //Summary:
            // - Payments: 3
            // - Balance: 93000.00
            let paymentsCount = this.payments.length;
            let balance = (() => {
                let b = 0;
                for (let i = 0; i < this.payments.length; i++) {
                    let current = this.payments[i];
                    b += parseFloat(current.value);
                }

                return b.toFixed(this.options.precision);
            })();

            let output = [
                "Summary:",
                ` - Payments: ${paymentsCount}`,
                ` - Balance: ${balance}`
            ];

            return output.join("\n");
        }
    }

    const generalPayments = new PaymentProcessor();
    generalPayments.setOptions({types: ['product', 'material']});
    generalPayments.registerPayment('E028', 'Rare-earth elements', 'material', 8000);
    console.log(generalPayments.get('E028'));
    generalPayments.registerPayment('CF15', 'Enzymes', 'material', 55000);

}

paymentProcessor();