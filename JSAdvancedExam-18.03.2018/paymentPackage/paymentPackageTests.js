let assert = require("chai").assert;
let expect = require("chai").expect;

let PaymentPackage = require("./paymentPackage").PaymentPackage;

describe("Test package", function () {

    describe("constructor", function () {
        it("test if constructor works correctly with 2 params", function () {
            let paymentPack = new PaymentPackage("Ivan", 498);

            assert.equal(paymentPack.hasOwnProperty("_name"), true);
            assert.equal(paymentPack.hasOwnProperty("_value"), true);
            assert.equal(paymentPack.hasOwnProperty("_VAT"), true);
            assert.equal(paymentPack.hasOwnProperty("_active"), true);

            assert.doesNotThrow(() => p = new PaymentPackage("ivan", 456));

            assert.typeOf(paymentPack._name, "string");
            assert.typeOf(paymentPack._value, "number");
            assert.typeOf(paymentPack._VAT, "number");
            assert.typeOf(paymentPack._active, "boolean");

            assert.equal(paymentPack._name, "Ivan");
            assert.equal(paymentPack._value, 498);
            assert.equal(paymentPack._VAT, 20);
            assert.equal(paymentPack._active, true);

        });

        it("should throw an error with 1 or wrong params", function () {
            expect(() => p = new PaymentPackage("Ivan")).to.throw(Error);
            expect(() => p = new PaymentPackage("Ivan", -213)).to.throw(Error);
            expect(() => p = new PaymentPackage({})).to.throw(Error);
            expect(() => p = new PaymentPackage({}, 200)).to.throw(Error);
            expect(() => p = new PaymentPackage([], 1025)).to.throw(Error);
            expect(() => p = new PaymentPackage(new Date("01.01.2018"), 200)).to.throw(Error);
            expect(() => p = new PaymentPackage("Ivan", [])).to.throw(Error);
            expect(() => p = new PaymentPackage("Ivan", [1])).to.throw(Error);
            expect(() => p = new PaymentPackage("Ivan", function () {
                return 1
            })).to.throw(Error);
            expect(() => p = new PaymentPackage("Ivan", -213)).to.throw(Error);
            expect(() => p = new PaymentPackage()).to.throw(Error);
        });
    });

    describe("setters", function () {
        it("name setter should throw error if value is non String or empty String", function () {
            let paymentPack = new PaymentPackage("Ivan", 498);

            assert.throw(() => paymentPack.name = {}, Error);
            assert.throw(() => paymentPack.name = new Date("02-30-2012"), Error);
            assert.throw(() => paymentPack.name = ["Ivan"], Error);
            assert.throw(() => paymentPack.name = 12, Error);
            assert.throw(() => paymentPack.name = "", Error);
            assert.throw(() => paymentPack.name = new String(), Error);
        });

        it("name setter should set correctly", function () {
            let paymentPack = new PaymentPackage("Ivan", 498);

            assert.doesNotThrow(() => paymentPack.name = "Ivan", Error);
            assert.equal(paymentPack.name, "Ivan");
            assert.equal(paymentPack.hasOwnProperty("_name"), true);

            assert.doesNotThrow(() => paymentPack.name = "IDHNAJSDUASOIHNDASDUISADNASUIASDUJASIDBASDIUASDHBASUJDASNBDA", Error);
            assert.equal(paymentPack.name, "IDHNAJSDUASOIHNDASDUISADNASUIASDUJASIDBASDIUASDHBASUJDASNBDA");
            assert.equal(paymentPack.hasOwnProperty("_name"), true);

            assert.doesNotThrow(() => paymentPack.name = "<script>function f() { while (true){} } f()</script>", Error);
            assert.equal(paymentPack.name, "<script>function f() { while (true){} } f()</script>");
            assert.equal(paymentPack.hasOwnProperty("_name"), true);
        });

        it("value setter should throw error if value is non number or is negative", function () {
            let paymentPack = new PaymentPackage("Ivan", 498);

            assert.throw(() => paymentPack.value = {}, Error);
            assert.equal(paymentPack.value, 498);

            assert.throw(() => paymentPack.value = "", Error);
            assert.equal(paymentPack.value, 498);

            assert.throw(() => paymentPack.value = new Date("09-11-2012"), Error);
            assert.equal(paymentPack.value, 498);

            assert.throw(() => paymentPack.value = Number.MIN_SAFE_INTEGER, Error);
            assert.equal(paymentPack.value, 498);

            assert.throw(() => paymentPack.value = Number.NEGATIVE_INFINITY, Error);
            assert.equal(paymentPack.value, 498);

            assert.throw(() => paymentPack.value = -1.000000010205050, Error);
            assert.equal(paymentPack.value, 498);

            assert.throw(() => paymentPack.value = -0.0000000000000000000000000000000000000000001, Error);
            assert.equal(paymentPack.value, 498);
        });

        it("value setter should set correctly", function () {
            let paymentPack = new PaymentPackage("Ivan", 498);

            assert.doesNotThrow(() => paymentPack.value = 0.1212121212121212121212121, Error);
            assert.equal(paymentPack.value, 0.1212121212121212121212121);
            assert.equal(paymentPack.hasOwnProperty("_value"), true);
            assert.equal(paymentPack._value, 0.1212121212121212121212121);

            assert.doesNotThrow(() => paymentPack.value = Number.MIN_VALUE, Error);
            assert.equal(paymentPack.value, Number.MIN_VALUE);
            assert.equal(paymentPack.hasOwnProperty("_value"), true);
            assert.equal(paymentPack._value, Number.MIN_VALUE);

            assert.doesNotThrow(() => paymentPack.value = Number.MAX_VALUE, Error);
            assert.equal(paymentPack.value, Number.MAX_VALUE);
            assert.equal(paymentPack.hasOwnProperty("_value"), true);
            assert.equal(paymentPack._value, Number.MAX_VALUE);
        });

        it("VAT setter should throw error if value is non number or is negative", function () {
            let paymentPack = new PaymentPackage("Ivan", 498);

            assert.throw(() => paymentPack.VAT = {}, Error);
            assert.equal(paymentPack.VAT, 20);

            assert.throw(() => paymentPack.VAT = "", Error);
            assert.equal(paymentPack.VAT, 20);

            assert.throw(() => paymentPack.VAT = new Date("09-11-2012"), Error);
            assert.equal(paymentPack.VAT, 20);

            assert.throw(() => paymentPack.VAT = Number.MIN_SAFE_INTEGER, Error);
            assert.equal(paymentPack.VAT, 20);

            assert.throw(() => paymentPack.VAT = Number.NEGATIVE_INFINITY, Error);
            assert.equal(paymentPack.VAT, 20);

            assert.throw(() => paymentPack.VAT = -1.000000010205050, Error);
            assert.equal(paymentPack.VAT, 20);

            assert.throw(() => paymentPack.VAT = -0.0000000000000000000000000000000000000000001, Error);
            assert.equal(paymentPack.VAT, 20);
        });

        it("VAT setter should set correctly", function () {
            let paymentPack = new PaymentPackage("Ivan", 498);

            assert.doesNotThrow(() => paymentPack.VAT = 0.1212121212121212121212121, Error);
            assert.equal(paymentPack.VAT, 0.1212121212121212121212121);
            assert.equal(paymentPack.hasOwnProperty("_VAT"), true);
            assert.equal(paymentPack._VAT, 0.1212121212121212121212121);

            assert.doesNotThrow(() => paymentPack.VAT = Number.MIN_VALUE, Error);
            assert.equal(paymentPack.VAT, Number.MIN_VALUE);
            assert.equal(paymentPack.hasOwnProperty("_VAT"), true);
            assert.equal(paymentPack._VAT, Number.MIN_VALUE);

            assert.doesNotThrow(() => paymentPack.VAT = Number.MAX_VALUE, Error);
            assert.equal(paymentPack.VAT, Number.MAX_VALUE);
            assert.equal(paymentPack.hasOwnProperty("_VAT"), true);
            assert.equal(paymentPack._VAT, Number.MAX_VALUE);
        });

        it("active setter should throw error if value is not boolean", function () {
            let paymentPack = new PaymentPackage("Ivan", 498);

            assert.throw(() => paymentPack.active = {}, Error);
            assert.equal(paymentPack.active, true);
            assert.typeOf(paymentPack.active, "boolean");

            assert.throw(() => paymentPack.active = 0, Error);
            assert.equal(paymentPack.active, true);
            assert.typeOf(paymentPack.active, "boolean");

            assert.throw(() => paymentPack.active = 1, Error);
            assert.equal(paymentPack.active, true);
            assert.typeOf(paymentPack.active, "boolean");

            assert.throw(() => paymentPack.active = null, Error);
            assert.equal(paymentPack.active, true);
            assert.typeOf(paymentPack.active, "boolean");

            assert.throw(() => paymentPack.active = [false], Error);
            assert.equal(paymentPack.active, true);
            assert.typeOf(paymentPack.active, "boolean");
        });

        it("active setter should set correctly", function () {
            let paymentPack = new PaymentPackage("Ivan", 498);

            assert.doesNotThrow(() => paymentPack.active = true, Error);
            assert.equal(paymentPack.active, true);
            assert.equal(paymentPack.hasOwnProperty("_active"), true);
            assert.equal(paymentPack._active, true);

            assert.doesNotThrow(() => paymentPack.active = false, Error);
            assert.equal(paymentPack.active, false);
            assert.equal(paymentPack.hasOwnProperty("_active"), true);
            assert.equal(paymentPack._active, false);
        });
    });

    describe("getters", function () {
        it('name getter should work properly', function () {
            let paymentPack = new PaymentPackage("Ivan", 498);

            assert.equal(paymentPack.name, "Ivan");
            assert.doesNotThrow(() => paymentPack.name = String("Peshoslav"));
            assert.isString(paymentPack.name);

            assert.equal(paymentPack.name, "Peshoslav");
        });

        it('value getter should work properly', function () {
            let paymentPack = new PaymentPackage("Ivan", 498);

            assert.equal(paymentPack.value, 498);
            assert.doesNotThrow(() => paymentPack.value = 150);
            assert.isNumber(paymentPack.value);

            assert.equal(paymentPack.value, 150);
        });

        it('VAT getter should work properly', function () {
            let paymentPack = new PaymentPackage("Ivan", 498);

            assert.equal(paymentPack.VAT, 20);
            assert.doesNotThrow(() => paymentPack.VAT = 40);
            assert.isNumber(paymentPack.VAT);

            assert.equal(paymentPack.VAT, 40);
            assert.equal(paymentPack.hasOwnProperty("_VAT"), true);
        });

        it('active getter should work properly', function () {
            let paymentPack = new PaymentPackage("Ivan", 498);

            assert.equal(paymentPack.active, true);
            assert.doesNotThrow(() => paymentPack.active = false);
            assert.isFalse(paymentPack.active);

            assert.equal(paymentPack.hasOwnProperty("_active"), true);
        });
    });

    describe("toString", function () {
        it("test toString function", function () {
            let paymentPack = new PaymentPackage("Ivan", 498);

            assert.equal(paymentPack.toString(), "Package: Ivan\n- Value (excl. VAT): 498\n- Value (VAT 20%): 597.6");

            assert.doesNotThrow(() => paymentPack.active = false);
            assert.equal(paymentPack.toString(), "Package: Ivan (inactive)\n- Value (excl. VAT): 498\n- Value (VAT 20%): 597.6");

            assert.doesNotThrow(() => paymentPack.value = 10);
            assert.doesNotThrow(() => paymentPack.active = true);
            assert.equal(paymentPack.toString(), "Package: Ivan\n- Value (excl. VAT): 10\n- Value (VAT 20%): 12");

            assert.doesNotThrow(() => paymentPack.name = "123");
            assert.doesNotThrow(() => paymentPack.value = 123);
            assert.equal(paymentPack.toString(), "Package: 123\n- Value (excl. VAT): 123\n- Value (VAT 20%): 147.6");

            assert.doesNotThrow(() => paymentPack.name = "0");
            assert.doesNotThrow(() => paymentPack.value = 0);
            assert.doesNotThrow(() => paymentPack.active = false);
            assert.equal(paymentPack.toString(), "Package: 0 (inactive)\n- Value (excl. VAT): 0\n- Value (VAT 20%): 0");
        });
    });
});