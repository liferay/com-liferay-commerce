import AddOrCreate from './AddOrCreate.es';
import {launcher} from '../utilities/entry';

export default (...data) => launcher(AddOrCreate, ...data);
