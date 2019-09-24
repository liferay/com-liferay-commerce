import AssignTo from './AssignTo.es';
import {launcher} from '../utilities/index.es';

const AssignToLauncher = (...data) => launcher(AssignTo, ...data);

export default AssignToLauncher;
