import ClayTable from "clay-table";
// import { Config } from "metal-state";
import "./MiniumTable.soy.js";

class MiniumTable extends ClayTable {
handleToggle(e) {
	const row = e.target.closest("tr");
	const width = row.querySelector(".actions").getBoundingClientRect().width;
	row.style.setProperty("--translate-space", `${width - 10}px`);
	row.classList.toggle("is-active");
}
}

// MiniumTable.STATE = {
//   showActions: Config.bool().value(false),
//   actionsWidth: Config.string()
// };

export { MiniumTable };
export default MiniumTable;