import TableToolbar from './TableToolbar.tsx';
import {launcher} from '../utilities/entry';

const TableToolbarLauncher = (...data) => launcher(TableToolbar, ...data);

export default TableToolbarLauncher;
