import AssignTo from './AssignTo.es';
import {launcher} from '../utilities/entry';

const AssignToLauncher = (...data) => launcher(AssignTo, ...data);

export default AssignToLauncher;
