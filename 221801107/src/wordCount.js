const path = require("path");
const fs = require("fs");
const { calCharacterCount } = require("./character");
const { calNoEmptyRowsCount } = require("./row");
const {
  calWordCount,
  // calSortedWordsFrequency,
  calSortedWordsFrequencyByHeap,
} = require("./word");

const SRC_PATH = path.join(__dirname);

const main = () => {
  try {
    console.log("--------------");
    console.log("Run program...");
    const argvs = process.argv;
    if (argvs.length < 4) {
      console.error("Error: please input two files");
      return;
    }

    const input = path.isAbsolute(argvs[2])
      ? argvs[2]
      : path.join(SRC_PATH, argvs[2]);
    const output = path.isAbsolute(argvs[3])
      ? argvs[3]
      : path.join(SRC_PATH, argvs[3]);


    if (!fs.existsSync(input)) {
      console.error("Error: readFile not exist");
      return;
    }

    const content = fs.readFileSync(input).toString();

    const writeContent = `characters: ${calCharacterCount(content)}\nwords: ${calWordCount(
      content
    )}\nlines: ${calNoEmptyRowsCount(content)}\n${calSortedWordsFrequencyByHeap(content, 10)
      .map((item) => `${item.word}: ${item.count}\n`)
      .join("")}`;

    fs.writeFileSync(output, writeContent);
    console.log("Finish Program...");
  } catch (ex) {
    console.error(ex.message);
    console.error("sorry, it must be some error in program");
  } finally {
    console.log("--------------");
  }
};

main();
