import java.util.ArrayList;
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
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        Resume resume = null;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid))
                resume = storage[i];
            break;
        }
        return resume;
    }

    void delete(String uuid) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                size--;
                index = i;
                break;
            }
        }
        System.arraycopy(storage, index + 1, storage, index, size + 1);
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
        for (int i = 0; i < storage.length; i++) {
            if (Objects.isNull(storage[i]))
                break;
            count++;
        }
        return count;
    }
}
