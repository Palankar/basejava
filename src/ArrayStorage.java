import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = size();

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        Resume resume = null;
        for (Resume r: storage) {
            if (!Objects.isNull(r) && r.uuid.equals(uuid)) {
                resume = r;
                break;
            }
        }
        return resume;
    }

    void delete(String uuid) {
        for (int i = 0; i < size - 1; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                size--;
                break;
            }
        }
        storage = getAll();
        //System.arraycopy(getAll(), 0, storage, 0, size);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allRes = new Resume[size];
        int count = 0;
        for (int i = 0; i < storage.length; i++) {
            if (!Objects.isNull(storage[i])) {
                allRes[count] = storage[i];
                count++;
            }
        }
        return allRes;
    }

    int size() {
        int count = 0;
        for (Resume resume : storage) {
            if (!Objects.isNull(resume))
                count++;
        }
        return count;
    }
}
